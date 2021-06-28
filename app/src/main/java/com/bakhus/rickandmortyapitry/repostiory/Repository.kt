package com.bakhus.rickandmortyapitry.repostiory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.locale.CharacterDatabase
import com.bakhus.rickandmortyapitry.models.CharacterPageSource

class Repository(
    private val apiHelper: ApiHelper
) {

    suspend fun getCharacters(page: Int) =
       apiHelper.getCharacters(page)

    suspend fun getLocations(page: Int) =
        apiHelper.getLocations(page)

    suspend fun getCharacterByName(name: String) =
        apiHelper.getCharacterByName(name)
}