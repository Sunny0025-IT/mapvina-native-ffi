package io.github.mapvina.nativeffi.error;

/** Thrown when a native call reports an invalid argument. */
public final class InvalidArgumentException extends MapvinaException {
  public InvalidArgumentException(int nativeStatusCode, String diagnostic) {
    super(MapvinaStatus.INVALID_ARGUMENT, nativeStatusCode, diagnostic);
  }
}
