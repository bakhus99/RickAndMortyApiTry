package com.bakhus.rickandmortyapitry.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.RawValue

@Keep
@kotlinx.parcelize.Parcelize
data class CharacterList(
    val info: @RawValue Info,
    val results: List<Character>

):Parcelable
