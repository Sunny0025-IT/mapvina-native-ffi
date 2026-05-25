package io.github.mapvina.android.testapp.activity.style

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.mapvina.geojson.Point
import io.github.mapvina.geojson.Polygon
import io.github.mapvina.android.camera.CameraPosition
import io.github.mapvina.android.camera.CameraUpdateFactory
import io.github.mapvina.android.geometry.LatLng
import io.github.mapvina.android.maps.MapView
import io.github.mapvina.android.maps.MapLibreMap
import io.github.mapvina.android.maps.OnMapReadyCallback
import io.github.mapvina.android.maps.Style
import io.github.mapvina.android.style.layers.*
import io.github.mapvina.android.style.sources.GeoJsonSource
import io.github.mapvina.android.testapp.R
import io.github.mapvina.android.testapp.styles.TestStyles
import java.util.*

/**
 * Test activity showcasing fill extrusions
 */
class FillExtrusionActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_extrusion_layer)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(
            OnMapReadyCallback { maplibreMap: MapLibreMap ->
                maplibreMap.setStyle(TestStyles.getPredefinedStyleWithFallback("Streets")) { style: Style ->
                    val lngLats = listOf(
                        Arrays.asList(
                            Point.fromLngLat(5.12112557888031, 52.09071040847704),
                            Point.fromLngLat(5.121227502822875, 52.09053901776669),
                            Point.fromLngLat(5.121484994888306, 52.090601641371805),
                            Point.fromLngLat(5.1213884353637695, 52.090766439912635),
                            Point.fromLngLat(5.12112557888031, 52.09071040847704)
                        )
                    )
                    val domTower = Polygon.fromLngLats(lngLats)
                    val source = GeoJsonSource("extrusion-source", domTower)
                    style.addSource(source)
                    style.addLayer(
                        FillExtrusionLayer("extrusion-layer", source.id)
                            .withProperties(
                                PropertyFactory.fillExtrusionHeight(40f),
                                PropertyFactory.fillExtrusionOpacity(0.5f),
                                PropertyFactory.fillExtrusionColor(Color.RED)
                            )
                    )
                    maplibreMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder()
                                .target(LatLng(52.09071040847704, 5.12112557888031))
                                .tilt(45.0)
                                .zoom(18.0)
                                .build()
                        ),
                        10000
                    )
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    public override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}
