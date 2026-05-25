package io.github.mapvina.nativeffi.error;

/** Thrown when a native object is in the wrong lifecycle state for a call. */
public final class InvalidStateException extends MapvinaException {
  public InvalidStateException(int nativeStatusCode, String diagnostic) {
    super(MapvinaStatus.INVALID_STATE, nativeStatusCode, diagnostic);
  }
}
