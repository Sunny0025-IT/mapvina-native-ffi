package io.github.mapvina.android.testapp.model.annotations

import io.github.mapvina.android.annotations.BaseMarkerOptions
import io.github.mapvina.android.annotations.Marker

class CountryMarker(
    baseMarkerOptions: BaseMarkerOptions<*, *>?,
    val abbrevName: String,
    val flagRes: Int
) : Marker(baseMarkerOptions)
