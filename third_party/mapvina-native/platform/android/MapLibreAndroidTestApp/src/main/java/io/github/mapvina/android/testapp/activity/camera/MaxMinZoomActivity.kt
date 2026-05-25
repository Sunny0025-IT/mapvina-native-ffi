package io.github.mapvina.android.testapp.activity.camera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.mapvina.android.maps.MapView
import io.github.mapvina.android.maps.MapLibreMap
import io.github.mapvina.android.maps.MapLibreMap.OnMapClickListener
import io.github.mapvina.android.maps.OnMapReadyCallback
import io.github.mapvina.android.maps.Style
import io.github.mapvina.android.testapp.R
import io.github.mapvina.android.testapp.styles.TestStyles
import timber.log.Timber

/** Test activity showcasing using maximum and minimum zoom levels to restrict camera movement. */
class MaxMinZoomActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var maplibreMap: MapLibreMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maxmin_zoom)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        mapView.addOnDidFinishLoadingStyleListener { Timber.d("Style Loaded") }
    }

    override fun onMapReady(map: MapLibreMap) {
        maplibreMap = map
        maplibreMap.setStyle(TestStyles.OPENFREEMAP_LIBERTY)
        // # --8<-- [start:zoomPreference]
        maplibreMap.setMinZoomPreference(3.0)
        maplibreMap.setMaxZoomPreference(5.0)
        // # --8<-- [end:zoomPreference]

        // # --8<-- [start:addOnMapClickListener]
        maplibreMap.addOnMapClickListener {
            if (this::maplibreMap.isInitialized) {
                maplibreMap.setStyle(Style.Builder().fromUri(TestStyles.AMERICANA))
            }
            true
        }
        // # --8<-- [end:addOnMapClickListener]
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
