package io.github.mapvina.android.testapp.activity.textureview

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import io.github.mapvina.android.maps.MapLibreMapOptions
import io.github.mapvina.android.maps.OnMapReadyCallback
import io.github.mapvina.android.testapp.activity.maplayout.DebugModeActivity
import io.github.mapvina.android.testapp.utils.NavUtils

/**
 * Test activity showcasing the different debug modes and allows to cycle between the default map styles.
 */
class TextureViewDebugModeActivity : DebugModeActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // activity uses singleInstance for testing purposes
                // code below provides a default navigation when using the app
                NavUtils.navigateHome(this@TextureViewDebugModeActivity)
            }
        })
    }

    override fun setupMapLibreMapOptions(): MapLibreMapOptions {
        val maplibreMapOptions = super.setupMapLibreMapOptions()
        maplibreMapOptions.textureMode(true)
        return maplibreMapOptions
    }
}
