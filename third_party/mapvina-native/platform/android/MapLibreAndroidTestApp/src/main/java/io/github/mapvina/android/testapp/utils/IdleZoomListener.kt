package io.github.mapvina.android.testapp.utils

import android.widget.TextView
import io.github.mapvina.android.maps.MapLibreMap
import io.github.mapvina.android.maps.MapLibreMap.OnCameraIdleListener
import io.github.mapvina.android.testapp.R

class IdleZoomListener(private val maplibreMap: MapLibreMap, private val textView: TextView) :
    OnCameraIdleListener {
    override fun onCameraIdle() {
        val context = textView.context
        val position = maplibreMap.cameraPosition
        textView.text = String.format(context.getString(R.string.debug_zoom), position.zoom)
    }
}
