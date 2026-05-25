package io.github.mapvina.nativeffi.error;

/** Thrown when a native Mapvina error or C++ exception crosses the C ABI as a status. */
public final class NativeErrorException extends MapvinaException {
  public NativeErrorException(int nativeStatusCode, String diagnostic) {
    super(MapvinaStatus.NATIVE_ERROR, nativeStatusCode, diagnostic);
  }
}
