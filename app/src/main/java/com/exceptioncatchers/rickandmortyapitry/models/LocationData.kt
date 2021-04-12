package com.exceptioncatchers.rickandmortyapitry.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationData(
    val name: String,
    val type: String,
    val dimension: String,
    val url: String,
    val residents:List<String>
) : Parcelable
