package io.github.mapvina.nativeffi.internal.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.foreign.Arena;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.github.mapvina.nativeffi.error.InvalidArgumentException;
import io.github.mapvina.nativeffi.error.MapvinaStatus;
import io.github.mapvina.nativeffi.internal.c.MapVinaNativeC;
import io.github.mapvina.nativeffi.internal.memory.MemoryUtil;
import io.github.mapvina.nativeffi.internal.memory.NativePointerUtil;
import io.github.mapvina.nativeffi.render.NativePointer;
import io.github.mapvina.nativeffi.test.NativeTestSupport;

final class StatusAndMemoryTest {
  @BeforeAll
  static void loadNativeLibrary() {
    NativeTestSupport.loadNativeLibrary();
  }

  @Test
  void statusConversionCapturesDiagnostics() {
    var error =
        assertThrows(
            InvalidArgumentException.class,
            () -> Status.check(MapVinaNativeC.mln_network_status_set(999_999)));
    assertEquals(MapvinaStatus.INVALID_ARGUMENT, error.status());
    assertTrue(error.diagnostic().contains("network status"));
  }

  @Test
  void nullTerminatedStringsRejectEmbeddedNul() {
    try (var arena = Arena.ofConfined()) {
      assertThrows(IllegalArgumentException.class, () -> MemoryUtil.allocateCString(arena, "a\0b"));
    }
  }

  @Test
  void nativePointerRoundTripsWithoutExposingMemorySegment() {
    assertEquals(
        NativePointer.NULL, NativePointerUtil.fromSegment(NativePointerUtil.toSegment(null)));
    assertEquals(
        NativePointer.NULL,
        NativePointerUtil.fromSegment(NativePointerUtil.toSegment(NativePointer.NULL)));
    var pointer = NativePointer.ofAddress(0x1234);
    assertEquals(pointer, NativePointerUtil.fromSegment(NativePointerUtil.toSegment(pointer)));
  }
}
