package com.bakhus.rickandmortyapitry.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
@Keep
@Parcelize
data class LocationData(
    val name: String,
    val type: String,
    val dimension: String,
    val url: String,
    val residents: List<String>
) : Parcelable
