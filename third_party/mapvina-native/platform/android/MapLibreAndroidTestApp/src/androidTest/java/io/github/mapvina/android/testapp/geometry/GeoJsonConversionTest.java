package io.github.mapvina.android.testapp.geometry;

import androidx.test.annotation.UiThreadTest;

import com.google.gson.JsonArray;
import io.github.mapvina.geojson.Feature;
import io.github.mapvina.geojson.Point;
import io.github.mapvina.geojson.Polygon;
import io.github.mapvina.android.geometry.LatLng;
import io.github.mapvina.android.style.expressions.Expression;
import io.github.mapvina.android.style.layers.PropertyFactory;
import io.github.mapvina.android.style.layers.SymbolLayer;
import io.github.mapvina.android.style.sources.GeoJsonSource;
import io.github.mapvina.android.testapp.action.MapLibreMapAction;
import io.github.mapvina.android.testapp.activity.EspressoTest;
import io.github.mapvina.android.testapp.utils.TestingAsyncUtils;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static io.github.mapvina.geojson.Feature.fromGeometry;
import static io.github.mapvina.geojson.FeatureCollection.fromFeatures;
import static io.github.mapvina.geojson.GeometryCollection.fromGeometries;
import static io.github.mapvina.geojson.LineString.fromLngLats;
import static io.github.mapvina.geojson.MultiLineString.fromLineString;
import static io.github.mapvina.geojson.MultiPolygon.fromPolygon;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;

/**
 * Instrumentation test to validate java geojson conversion to c++
 */
public class GeoJsonConversionTest extends EspressoTest {

  // Regression test for #12343
  @Test
  @UiThreadTest
  public void testEmptyFeatureCollection() {
    validateTestSetup();
    maplibreMap.getStyle().addSource(
      new GeoJsonSource("test-id",
        fromFeatures(singletonList(fromGeometry(fromGeometries(emptyList()))))
      )
    );
    maplibreMap.getStyle().addLayer(new SymbolLayer("test-id", "test-id"));
  }

  @Test
  @UiThreadTest
  public void testPointFeatureCollection() {
    validateTestSetup();
    maplibreMap.getStyle().addSource(
      new GeoJsonSource("test-id",
        fromFeatures(singletonList(fromGeometry(Point.fromLngLat(0.0, 0.0))))
      )
    );
    maplibreMap.getStyle().addLayer(new SymbolLayer("test-id", "test-id"));
  }

  @Test
  @UiThreadTest
  public void testMultiPointFeatureCollection() {
    validateTestSetup();
    maplibreMap.getStyle().addSource(
      new GeoJsonSource("test-id",
        fromFeatures(singletonList(fromGeometry(fromLngLats(emptyList()))))
      )
    );
    maplibreMap.getStyle().addLayer(new SymbolLayer("test-id", "test-id"));
  }

  @Test
  @UiThreadTest
  public void testPolygonFeatureCollection() {
    validateTestSetup();
    maplibreMap.getStyle().addSource(
      new GeoJsonSource("test-id",
        fromFeatures(singletonList(fromGeometry(Polygon.fromLngLats(emptyList()))))
      )
    );
    maplibreMap.getStyle().addLayer(new SymbolLayer("test-id", "test-id"));
  }

  @Test
  @UiThreadTest
  public void testMultiPolygonFeatureCollection() {
    validateTestSetup();
    maplibreMap.getStyle().addSource(
      new GeoJsonSource("test-id",
        fromFeatures(singletonList(fromGeometry(fromPolygon(Polygon.fromLngLats(emptyList())))))
      )
    );
    maplibreMap.getStyle().addLayer(new SymbolLayer("test-id", "test-id"));
  }

  @Test
  @UiThreadTest
  public void testLineStringFeatureCollection() {
    validateTestSetup();
    maplibreMap.getStyle().addSource(
      new GeoJsonSource("test-id",
        fromFeatures(singletonList(fromGeometry(fromLngLats(emptyList()))))
      )
    );
    maplibreMap.getStyle().addLayer(new SymbolLayer("test-id", "test-id"));
  }

  @Test
  @UiThreadTest
  public void testMultiLineStringFeatureCollection() {
    validateTestSetup();
    maplibreMap.getStyle().addSource(
      new GeoJsonSource("test-id",
        fromFeatures(singletonList(fromGeometry(fromLineString(fromLngLats(emptyList())))))
      )
    );
    maplibreMap.getStyle().addLayer(new SymbolLayer("test-id", "test-id"));
  }


  @Test
  public void testNegativeNumberPropertyConversion() {
    validateTestSetup();
    onView(isRoot()).perform(new MapLibreMapAction((uiController, maplibreMap) -> {
      LatLng latLng = new LatLng();
      Feature feature = Feature.fromGeometry(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));

      JsonArray foregroundJsonArray = new JsonArray();
      foregroundJsonArray.add(0f);
      foregroundJsonArray.add(-3f);
      feature.addProperty("property", foregroundJsonArray);

      GeoJsonSource source = new GeoJsonSource("source", feature);
      maplibreMap.getStyle().addSource(source);

      SymbolLayer layer = new SymbolLayer("layer", "source")
        .withProperties(
          PropertyFactory.iconOffset(Expression.get("property")),
          PropertyFactory.iconImage("icon-zoo")
        );
      maplibreMap.getStyle().addLayer(layer);

      TestingAsyncUtils.INSTANCE.waitForLayer(uiController, mapView);

      assertFalse(maplibreMap.queryRenderedFeatures(maplibreMap.getProjection().toScreenLocation(latLng)).isEmpty());
    }, maplibreMap));
  }
}
