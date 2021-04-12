package com.exceptioncatchers.rickandmortyapitry.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCharacters(page: Int) = apiService.getCharacter(page)

    suspend fun getLocations(page: Int) = apiService.getLocation(page)

    suspend fun getCharacterByName(name: String) = apiService.getCharacterByName(name)

}