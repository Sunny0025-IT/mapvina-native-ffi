package io.github.mapvina.android.testapp.activity.render

import android.content.Context
import io.github.mapvina.android.snapshotter.MapSnapshot
import io.github.mapvina.android.snapshotter.MapSnapshotter

class RenderTestSnapshotter internal constructor(context: Context, options: Options) :
    MapSnapshotter(context, options) {
    override fun addOverlay(mapSnapshot: MapSnapshot) {
        // don't add an overlay
    }
}
