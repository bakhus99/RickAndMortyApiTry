package com.bakhus.rickandmortyapitry.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bakhus.rickandmortyapitry.repostiory.Repository
import com.bakhus.rickandmortyapitry.utils.Resource
import kotlinx.coroutines.Dispatchers

class LocationsViewModel(private val repository: Repository) : ViewModel() {

    fun getLocations(page: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getLocations(page)))
        }catch (e:Exception){
            emit(Resource.error(data = null,message = e.message?: "Data error"))
        }
    }

}