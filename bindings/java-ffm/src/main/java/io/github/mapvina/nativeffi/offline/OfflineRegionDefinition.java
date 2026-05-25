package io.github.mapvina.nativeffi.offline;

import java.util.Objects;
import io.github.mapvina.nativeffi.geo.Geometry;
import io.github.mapvina.nativeffi.geo.LatLngBounds;

/** Offline region definition copied into native storage at creation time. */
public sealed interface OfflineRegionDefinition
    permits OfflineRegionDefinition.TilePyramid, OfflineRegionDefinition.GeometryRegion {
  record TilePyramid(
      String styleUrl,
      LatLngBounds bounds,
      double minZoom,
      double maxZoom,
      float pixelRatio,
      boolean includeIdeographs)
      implements OfflineRegionDefinition {
    public TilePyramid {
      Objects.requireNonNull(styleUrl, "styleUrl");
      Objects.requireNonNull(bounds, "bounds");
    }
  }

  record GeometryRegion(
      String styleUrl,
      Geometry geometry,
      double minZoom,
      double maxZoom,
      float pixelRatio,
      boolean includeIdeographs)
      implements OfflineRegionDefinition {
    public GeometryRegion {
      Objects.requireNonNull(styleUrl, "styleUrl");
      Objects.requireNonNull(geometry, "geometry");
    }
  }
}
