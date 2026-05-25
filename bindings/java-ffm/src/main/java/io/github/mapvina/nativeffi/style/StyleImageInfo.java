package io.github.mapvina.nativeffi.style;

/** Copied metadata for one runtime style image. */
public record StyleImageInfo(
    int width, int height, int stride, long byteLength, float pixelRatio, boolean sdf) {}
