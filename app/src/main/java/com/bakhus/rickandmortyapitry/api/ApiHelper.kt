package com.bakhus.rickandmortyapitry.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.bakhus.rickandmortyapitry.models.CharacterPageSource

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCharacters(page: Int) =
    apiService.getCharacter(page)

    suspend fun getLocations(page: Int) = apiService.getLocation(page)

    suspend fun getCharacterByName(name: String) = apiService.getCharacterByName(name)

}