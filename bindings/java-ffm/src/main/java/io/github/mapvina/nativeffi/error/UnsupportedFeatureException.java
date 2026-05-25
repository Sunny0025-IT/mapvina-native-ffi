package io.github.mapvina.nativeffi.error;

/** Thrown when the native library does not support the requested feature. */
public final class UnsupportedFeatureException extends MapvinaException {
  public UnsupportedFeatureException(int nativeStatusCode, String diagnostic) {
    super(MapvinaStatus.UNSUPPORTED, nativeStatusCode, diagnostic);
  }
}
