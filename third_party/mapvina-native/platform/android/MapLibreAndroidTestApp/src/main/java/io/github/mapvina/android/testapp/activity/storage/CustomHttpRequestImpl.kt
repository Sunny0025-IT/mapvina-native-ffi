package io.github.mapvina.android.testapp.activity.storage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.github.mapvina.android.ModuleProvider
import io.github.mapvina.android.ModuleProviderImpl
import io.github.mapvina.android.maps.MapView
import io.github.mapvina.android.MapLibre
import io.github.mapvina.android.maps.MapLibreMap
import io.github.mapvina.android.maps.OnMapReadyCallback
import io.github.mapvina.android.maps.Style
import io.github.mapvina.android.storage.FileSource
import io.github.mapvina.android.storage.FileSource.ResourceTransformCallback
import io.github.mapvina.android.storage.Resource
import io.github.mapvina.android.testapp.R
import io.github.mapvina.android.testapp.utils.ApiKeyUtils
import io.github.mapvina.android.testapp.utils.ExampleCustomModuleProviderImpl
import timber.log.Timber

/**
 * This example activity shows how to provide your own HTTP request implementation.
 */
class CustomHttpRequestImplActivity : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_driven_style)

        // Set a custom module provider that provides our custom HTTPRequestImpl
        MapLibre.setModuleProvider(ExampleCustomModuleProviderImpl() as ModuleProvider)

        // Initialize map with a style
        mapView = findViewById<View>(R.id.mapView) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(
            OnMapReadyCallback { maplibreMap: MapLibreMap ->
                maplibreMap.setStyle(Style.Builder().fromUri("https://demotiles.maplibre.org/style.json"))
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Example of how to reset the module provider
        MapLibre.setModuleProvider(ModuleProviderImpl())
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
