package com.bakhus.rickandmortyapitry.models

import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@kotlinx.parcelize.Parcelize
data class LocationData(
    val name: String,
    val type: String,
    val dimension: String,
    val url: String,
   // val residents: List<String>
) : Parcelable
