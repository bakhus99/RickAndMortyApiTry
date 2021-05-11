package com.bakhus.rickandmortyapitry.models

import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@kotlinx.parcelize.Parcelize
data class CharacterList(
    val results: List<Character>
):Parcelable
