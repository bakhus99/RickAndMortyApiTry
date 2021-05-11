package com.bakhus.rickandmortyapitry.repostiory

import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.locale.CharacterDatabase

class Repository(
    private val apiHelper: ApiHelper,
    val db: CharacterDatabase
) {

    suspend fun getCharacters(page: Int) =
        apiHelper.getCharacters(page)

    suspend fun getLocations(page: Int) =
        apiHelper.getLocations(page)

    suspend fun getCharacterByName(name: String) =
        apiHelper.getCharacterByName(name)

    fun getCharactersFromBd() = db.characterDao().getAllCharacters()
}