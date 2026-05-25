package io.github.mapvina.nativeffi.error;

/** Thrown when an owner-thread-affine native handle is used from the wrong thread. */
public final class WrongThreadException extends MapvinaException {
  public WrongThreadException(int nativeStatusCode, String diagnostic) {
    super(MapvinaStatus.WRONG_THREAD, nativeStatusCode, diagnostic);
  }
}
