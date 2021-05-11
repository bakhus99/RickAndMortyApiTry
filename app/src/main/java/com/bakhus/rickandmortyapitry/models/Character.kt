package com.bakhus.rickandmortyapitry.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@kotlinx.parcelize.Parcelize
@Entity(tableName = "character_table")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin : LocationData,
    val location : LocationData,
    val image : String,
    val episode : List<String>
):Parcelable


