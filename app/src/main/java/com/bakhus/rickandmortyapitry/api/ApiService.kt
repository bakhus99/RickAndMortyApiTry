package com.bakhus.rickandmortyapitry.api

import androidx.annotation.IntRange
import com.bakhus.rickandmortyapitry.models.CharacterList
import com.bakhus.rickandmortyapitry.models.LocationsList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacter(@Query("page") @IntRange(from = 1) page: Int = 1): CharacterList

    @GET("location")
    suspend fun getLocation(@Query("page") page: Int): LocationsList

    @GET("character")
    suspend fun getCharacterByName(@Query("name") name: String): CharacterList
}