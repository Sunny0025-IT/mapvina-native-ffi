package io.github.mapvina.android.testapp.activity.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import io.github.mapvina.android.maps.MapLibreMap
import io.github.mapvina.android.maps.OnMapReadyCallback
import io.github.mapvina.android.maps.SupportMapFragment
import io.github.mapvina.android.testapp.R
import io.github.mapvina.android.testapp.styles.TestStyles

/**
 * Test Activity showcasing using multiple static map fragments in one layout.
 */
class MultiMapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_map)
        val fragmentManager = supportFragmentManager
        initFragmentStyle(fragmentManager, R.id.map1, TestStyles.getPredefinedStyleWithFallback("Streets"))
        initFragmentStyle(fragmentManager, R.id.map2, TestStyles.getPredefinedStyleWithFallback("Bright"))
        initFragmentStyle(fragmentManager, R.id.map3, TestStyles.getPredefinedStyleWithFallback("Satellite Hybrid"))
        initFragmentStyle(fragmentManager, R.id.map4, TestStyles.getPredefinedStyleWithFallback("Pastel"))
    }

    private fun initFragmentStyle(
        fragmentManager: FragmentManager,
        fragmentId: Int,
        styleId: String
    ) {
        (fragmentManager.findFragmentById(fragmentId) as SupportMapFragment?)
            ?.getMapAsync(OnMapReadyCallback { maplibreMap: MapLibreMap -> maplibreMap.setStyle(styleId) })
    }
}
