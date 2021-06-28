package com.bakhus.rickandmortyapitry.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.locale.CharacterDatabase
import com.bakhus.rickandmortyapitry.repostiory.Repository
import com.bakhus.rickandmortyapitry.ui.main.viewmodel.LocationsViewModel

@Suppress("UNCHECKED_CAST")
class LocationsViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationsViewModel::class.java)) {
            return LocationsViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}