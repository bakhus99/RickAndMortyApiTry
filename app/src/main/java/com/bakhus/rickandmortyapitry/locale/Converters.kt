package com.bakhus.rickandmortyapitry.locale

import androidx.room.TypeConverter
import com.bakhus.rickandmortyapitry.models.LocationData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromLocation(locationData: LocationData): String {
        return locationData.name
    }

    @TypeConverter
    fun toLocation(name: String): LocationData {
        return LocationData(name, name,name,name)
    }

    @TypeConverter
    fun fromListStringEpisodes(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toListEpisodes(list: List<String?>): String {
        return Gson().toJson(list)
    }

}