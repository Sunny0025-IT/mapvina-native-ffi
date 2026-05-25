package io.github.mapvina.android.maps;


import androidx.annotation.NonNull;

import io.github.mapvina.android.annotations.Polyline;
import io.github.mapvina.android.annotations.PolylineOptions;

import java.util.List;

/**
 * Interface that defines convenient methods for working with a {@link Polyline}'s collection.
 */
interface Polylines {
  Polyline addBy(@NonNull PolylineOptions polylineOptions, @NonNull MapLibreMap maplibreMap);

  List<Polyline> addBy(@NonNull List<PolylineOptions> polylineOptionsList, @NonNull MapLibreMap maplibreMap);

  void update(Polyline polyline);

  List<Polyline> obtainAll();
}
