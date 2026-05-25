package io.github.mapvina.android.testapp.annotations;

import android.graphics.Color;

import io.github.mapvina.android.annotations.Polyline;
import io.github.mapvina.android.annotations.PolylineOptions;
import io.github.mapvina.android.geometry.LatLng;
import io.github.mapvina.android.testapp.activity.EspressoTest;

import org.junit.Ignore;
import org.junit.Test;

import static io.github.mapvina.android.testapp.action.MapLibreMapAction.invoke;
import static org.junit.Assert.assertEquals;

public class PolylineTest extends EspressoTest {

  @Test
  @Ignore
  public void addPolylineTest() {
    validateTestSetup();
    invoke(maplibreMap, (uiController, maplibreMap) -> {
      LatLng latLngOne = new LatLng();
      LatLng latLngTwo = new LatLng(1, 0);

      assertEquals("Polygons should be empty", 0, maplibreMap.getPolygons().size());

      final PolylineOptions options = new PolylineOptions();
      options.color(Color.BLUE);
      options.add(latLngOne);
      options.add(latLngTwo);
      Polyline polyline = maplibreMap.addPolyline(options);

      assertEquals("Polylines should be 1", 1, maplibreMap.getPolylines().size());
      assertEquals("Polyline id should be 0", 0, polyline.getId());
      assertEquals("Polyline points size should match", 2, polyline.getPoints().size());
      assertEquals("Polyline stroke color should match", Color.BLUE, polyline.getColor());
      maplibreMap.clear();
      assertEquals("Polyline should be empty", 0, maplibreMap.getPolylines().size());
    });
  }
}
