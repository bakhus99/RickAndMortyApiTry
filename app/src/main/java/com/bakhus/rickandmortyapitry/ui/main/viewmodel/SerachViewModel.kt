package com.bakhus.rickandmortyapitry.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bakhus.rickandmortyapitry.repostiory.Repository
import com.bakhus.rickandmortyapitry.utils.Resource
import kotlinx.coroutines.Dispatchers

class SerachViewModel(private val repository: Repository) : ViewModel() {

    fun getCharacterByName(name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = repository.getCharacterByName(name)))
            } catch (e: Exception) {
                emit(Resource.error(data = null, e.message ?: "Error"))
            }

    }

}