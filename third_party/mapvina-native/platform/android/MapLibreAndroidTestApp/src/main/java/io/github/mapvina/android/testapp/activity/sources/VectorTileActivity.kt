package io.github.mapvina.android.testapp.activity.sources

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.mapvina.android.camera.CameraUpdateFactory
import io.github.mapvina.android.geometry.LatLngBounds
import io.github.mapvina.android.maps.MapView
import io.github.mapvina.android.style.layers.LineLayer
import io.github.mapvina.android.style.layers.PropertyFactory.lineColor
import io.github.mapvina.android.style.layers.PropertyFactory.lineWidth
import io.github.mapvina.android.style.sources.TileSet
import io.github.mapvina.android.style.sources.VectorSource
import io.github.mapvina.android.testapp.R
import io.github.mapvina.android.testapp.styles.TestStyles


class VectorTileActivity : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vector_tile)
        mapView = findViewById<MapView>(R.id.mapView)

        mapView.getMapAsync {
            it.animateCamera(
                CameraUpdateFactory.newLatLngBounds(
                    // z: 12, x: 2177, y: 1436 is one of the available tiles:
                    // https://github.io/github/mapvina/demotiles/tree/gh-pages/tiles-omt/12/2177
                    LatLngBounds.from(12, 2177, 1436),
                    0
                )
            )
            it.setStyle(TestStyles.PROTOMAPS_GRAYSCALE) { style ->
                // --8<-- [start:addTileSet]
                val tileset = TileSet(
                    "openmaptiles",
                    "https://demotiles.maplibre.org/tiles-omt/{z}/{x}/{y}.pbf"
                )
                val openmaptiles = VectorSource("openmaptiles", tileset)
                style.addSource(openmaptiles)
                val roadLayer = LineLayer("road", "openmaptiles").apply {
                    setSourceLayer("transportation")
                    setProperties(
                        lineColor("red"),
                        lineWidth(2.0f)
                    )
                }
                // --8<-- [end:addTileSet]

                style.addLayer(roadLayer)
            }
        }
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

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}
