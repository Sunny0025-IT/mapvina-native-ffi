package io.github.mapvina.android.maps;

import android.graphics.RectF;

import io.github.mapvina.android.annotations.Annotation;

import java.util.List;

interface ShapeAnnotations {

  List<Annotation> obtainAllIn(RectF rectF);

}
