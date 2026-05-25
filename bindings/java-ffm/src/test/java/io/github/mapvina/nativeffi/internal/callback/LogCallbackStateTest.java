package io.github.mapvina.nativeffi.internal.callback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.github.mapvina.nativeffi.Mapvina;
import io.github.mapvina.nativeffi.internal.c.MapVinaNativeC;
import io.github.mapvina.nativeffi.internal.c.mln_log_callback;
import io.github.mapvina.nativeffi.internal.memory.MemoryUtil;
import io.github.mapvina.nativeffi.log.LogRecord;
import io.github.mapvina.nativeffi.log.LogSeverity;
import io.github.mapvina.nativeffi.test.NativeTestSupport;

final class LogCallbackStateTest {
  @BeforeAll
  static void loadNativeLibrary() {
    NativeTestSupport.loadNativeLibrary();
  }

  @AfterEach
  void clearCallback() {
    Mapvina.clearLogCallback();
  }

  @Test
  void callbackStateSurvivesUntilCleared() {
    var seen = new AtomicReference<LogRecord>();
    Mapvina.setLogCallback(
        record -> {
          seen.set(record);
          return true;
        });

    var state = LogCallbackState.currentForTesting();
    assertNotNull(state);
    try (var arena = Arena.ofConfined()) {
      var message = MemoryUtil.allocateCString(arena, "hello");
      var consumed =
          mln_log_callback.invoke(
              state.stubForTesting(),
              MemorySegment.NULL,
              MapVinaNativeC.MLN_LOG_SEVERITY_INFO(),
              MapVinaNativeC.MLN_LOG_EVENT_GENERAL(),
              7,
              message);
      assertEquals(1, consumed);
      assertEquals("hello", seen.get().message());
      assertEquals(LogSeverity.INFO, seen.get().severity());
    }
  }

  @Test
  void callbackExceptionsDoNotUnwindIntoNativeCode() {
    Mapvina.setLogCallback(
        record -> {
          throw new AssertionError("boom");
        });

    var state = LogCallbackState.currentForTesting();
    assertNotNull(state);
    try (var arena = Arena.ofConfined()) {
      var message = MemoryUtil.allocateCString(arena, "ignored");
      var consumed =
          mln_log_callback.invoke(
              state.stubForTesting(),
              MemorySegment.NULL,
              MapVinaNativeC.MLN_LOG_SEVERITY_ERROR(),
              MapVinaNativeC.MLN_LOG_EVENT_GENERAL(),
              0,
              message);
      assertEquals(0, consumed);
    }
  }
}
