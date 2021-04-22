package com.bakhus.rickandmortyapitry.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
@Keep
@Parcelize
data class CharacterList(
    val results: List<Character>
):Parcelable
