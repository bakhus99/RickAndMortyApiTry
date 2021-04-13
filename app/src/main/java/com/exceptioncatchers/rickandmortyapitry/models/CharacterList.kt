package com.exceptioncatchers.rickandmortyapitry.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterList(
    val results: List<Character>
):Parcelable
