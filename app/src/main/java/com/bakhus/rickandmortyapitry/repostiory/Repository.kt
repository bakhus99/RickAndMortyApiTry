package com.bakhus.rickandmortyapitry.repostiory

import com.bakhus.rickandmortyapitry.api.ApiHelper

class Repository(private val apiHelper: ApiHelper) {

    suspend fun getCharacters(page: Int) =
        apiHelper.getCharacters(page)

    suspend fun getLocations(page: Int) =
        apiHelper.getLocations(page)

    suspend fun getCharacterByName(name: String) =
        apiHelper.getCharacterByName(name)

}