package com.bakhus.rickandmortyapitry.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.bakhus.rickandmortyapitry.models.LocationsPagingSource
import com.bakhus.rickandmortyapitry.repostiory.Repository
import com.bakhus.rickandmortyapitry.utils.Constance.PAGE_SIZE
import com.bakhus.rickandmortyapitry.utils.Constance.PREFETCH_DISTANCE
import com.bakhus.rickandmortyapitry.utils.Resource
import kotlinx.coroutines.Dispatchers

class LocationsViewModel(private val repository: Repository) : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        LocationsPagingSource(repository)
    }.flow
        .cachedIn(viewModelScope)


    fun getLocations(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getLocations(page)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Data error"))
        }
    }



}