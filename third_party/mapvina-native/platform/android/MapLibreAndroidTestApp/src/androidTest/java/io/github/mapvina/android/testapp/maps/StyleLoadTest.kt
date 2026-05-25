package io.github.mapvina.android.testapp.maps

import androidx.test.espresso.UiController
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.github.mapvina.android.maps.MapLibreMap
import io.github.mapvina.android.maps.Style
import io.github.mapvina.android.style.layers.SymbolLayer
import io.github.mapvina.android.style.sources.GeoJsonSource
import io.github.mapvina.android.testapp.action.MapLibreMapAction
import io.github.mapvina.android.testapp.activity.EspressoTest
import io.github.mapvina.android.testapp.utils.TestingAsyncUtils
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class StyleLoadTest : EspressoTest() {

    @Test
    fun updateSourceAfterStyleLoad() {
        validateTestSetup()
        MapLibreMapAction.invoke(maplibreMap) { uiController: UiController, maplibreMap: MapLibreMap ->
            val source = GeoJsonSource("id")
            val layer = SymbolLayer("id", "id")
            maplibreMap.setStyle(Style.Builder().withSource(source).withLayer(layer))
            TestingAsyncUtils.waitForLayer(uiController, mapView)
            maplibreMap.setStyle(Style.getPredefinedStyles()[0].url)
            TestingAsyncUtils.waitForLayer(uiController, mapView)
            source.setGeoJson("{}")
        }
    }
}
