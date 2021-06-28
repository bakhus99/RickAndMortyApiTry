package com.bakhus.rickandmortyapitry.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.bakhus.rickandmortyapitry.models.CharacterPageSource
import com.bakhus.rickandmortyapitry.models.LocationsPagingSource
import com.bakhus.rickandmortyapitry.repostiory.Repository
import com.bakhus.rickandmortyapitry.utils.Constance
import com.bakhus.rickandmortyapitry.utils.Resource
import kotlinx.coroutines.Dispatchers

class CharactersViewModel(private val repository: Repository) : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = Constance.PAGE_SIZE,
            prefetchDistance = Constance.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        CharacterPageSource(repository)
    }.flow
        .cachedIn(viewModelScope)

    fun getCharacters(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getCharacters(page)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
}