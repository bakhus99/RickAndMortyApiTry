package com.bakhus.rickandmortyapitry.models

import androidx.annotation.Keep

@Keep
data class LocationsList(
    val info: Info,
    val results: List<LocationData>
)
