package io.github.mapvina.android.maps;

import android.graphics.RectF;

import androidx.annotation.NonNull;

import io.github.mapvina.android.annotations.BaseMarkerOptions;
import io.github.mapvina.android.annotations.Marker;

import java.util.List;

/**
 * Interface that defines convenient methods for working with a {@link Marker}'s collection.
 */
interface Markers {
  Marker addBy(@NonNull BaseMarkerOptions markerOptions, @NonNull MapLibreMap maplibreMap);

  List<Marker> addBy(@NonNull List<? extends BaseMarkerOptions> markerOptionsList, @NonNull MapLibreMap maplibreMap);

  void update(@NonNull Marker updatedMarker, @NonNull MapLibreMap maplibreMap);

  List<Marker> obtainAll();

  @NonNull
  List<Marker> obtainAllIn(@NonNull RectF rectangle);

  void reload();
}
