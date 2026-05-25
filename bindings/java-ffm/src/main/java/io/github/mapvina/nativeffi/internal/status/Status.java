package io.github.mapvina.nativeffi.internal.status;

import io.github.mapvina.nativeffi.error.InvalidStateException;
import io.github.mapvina.nativeffi.error.MapvinaException;
import io.github.mapvina.nativeffi.error.MapvinaStatus;
import io.github.mapvina.nativeffi.internal.c.MapVinaNativeC;
import io.github.mapvina.nativeffi.internal.memory.MemoryUtil;

/** Converts native status codes into public Java exceptions. */
public final class Status {
  private Status() {}

  public static void check(int nativeStatus) {
    var status = MapvinaStatus.fromNative(nativeStatus);
    if (status == MapvinaStatus.OK) {
      return;
    }
    throw MapvinaException.forStatus(status, nativeStatus, captureDiagnostic());
  }

  public static InvalidStateException released(String typeName) {
    return new InvalidStateException(
        MapvinaStatus.INVALID_STATE.nativeCode(), typeName + " is already closed");
  }

  public static String captureDiagnostic() {
    return MemoryUtil.copyCString(MapVinaNativeC.mln_thread_last_error_message());
  }
}
