package io.github.mapvina.android.testapp.model.annotations

import io.github.mapvina.android.annotations.Marker

class CityStateMarker(
    cityStateOptions: CityStateMarkerOptions?,
    val infoWindowBackgroundColor: String
) : Marker(cityStateOptions)
