package io.github.mapvina.android.testapp.activity.infowindow

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.github.mapvina.android.annotations.Marker
import io.github.mapvina.android.geometry.LatLng
import io.github.mapvina.android.maps.MapView
import io.github.mapvina.android.maps.MapLibreMap
import io.github.mapvina.android.maps.MapLibreMap.InfoWindowAdapter
import io.github.mapvina.android.maps.OnMapReadyCallback
import io.github.mapvina.android.maps.Style
import io.github.mapvina.android.testapp.R
import io.github.mapvina.android.testapp.model.annotations.CityStateMarker
import io.github.mapvina.android.testapp.model.annotations.CityStateMarkerOptions
import io.github.mapvina.android.testapp.styles.TestStyles
import io.github.mapvina.android.testapp.utils.IconUtils

/**
 * Test activity showcasing using an InfoWindowAdapter to provide a custom InfoWindow content.
 */
class InfoWindowAdapterActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var maplibreMap: MapLibreMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infowindow_adapter)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(
            OnMapReadyCallback { map: MapLibreMap ->
                maplibreMap = map
                map.setStyle(TestStyles.getPredefinedStyleWithFallback("Streets")) { style: Style? ->
                    addMarkers()
                    addCustomInfoWindowAdapter()
                }
            }
        )
    }

    private fun addMarkers() {
        maplibreMap.addMarker(generateCityStateMarker("Andorra", 42.505777, 1.52529, "#F44336"))
        maplibreMap.addMarker(generateCityStateMarker("Luxembourg", 49.815273, 6.129583, "#3F51B5"))
        maplibreMap.addMarker(generateCityStateMarker("Monaco", 43.738418, 7.424616, "#673AB7"))
        maplibreMap.addMarker(
            generateCityStateMarker(
                "Vatican City",
                41.902916,
                12.453389,
                "#009688"
            )
        )
        maplibreMap.addMarker(
            generateCityStateMarker(
                "San Marino",
                43.942360,
                12.457777,
                "#795548"
            )
        )
        maplibreMap.addMarker(
            generateCityStateMarker(
                "Liechtenstein",
                47.166000,
                9.555373,
                "#FF5722"
            )
        )
    }

    private fun generateCityStateMarker(
        title: String,
        lat: Double,
        lng: Double,
        color: String
    ): CityStateMarkerOptions {
        val marker = CityStateMarkerOptions()
        marker.title(title)
        marker.position(LatLng(lat, lng))
        marker.infoWindowBackground(color)
        val icon =
            IconUtils.drawableToIcon(this, R.drawable.ic_location_city, Color.parseColor(color))
        marker.icon(icon)
        return marker
    }

    private fun addCustomInfoWindowAdapter() {
        maplibreMap.infoWindowAdapter = object : InfoWindowAdapter {
            private val tenDp = resources.getDimension(R.dimen.attr_margin).toInt()
            override fun getInfoWindow(marker: Marker): View? {
                val textView = TextView(this@InfoWindowAdapterActivity)
                textView.text = marker.title
                textView.setTextColor(Color.WHITE)
                if (marker is CityStateMarker) {
                    textView.setBackgroundColor(Color.parseColor(marker.infoWindowBackgroundColor))
                }
                textView.setPadding(tenDp, tenDp, tenDp, tenDp)
                return textView
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
