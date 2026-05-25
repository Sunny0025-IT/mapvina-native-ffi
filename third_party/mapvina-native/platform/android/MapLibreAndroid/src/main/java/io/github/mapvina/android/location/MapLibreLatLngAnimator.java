package io.github.mapvina.android.location;

import android.animation.TypeEvaluator;

import androidx.annotation.NonNull;

import io.github.mapvina.android.geometry.LatLng;

class MapLibreLatLngAnimator extends MapLibreAnimator<LatLng> {

  MapLibreLatLngAnimator(@NonNull LatLng[] values, @NonNull AnimationsValueChangeListener updateListener,
                         int maxAnimationFps) {
    super(values, updateListener, maxAnimationFps);
  }

  @NonNull
  @Override
  TypeEvaluator provideEvaluator() {
    return new LatLngEvaluator();
  }
}
