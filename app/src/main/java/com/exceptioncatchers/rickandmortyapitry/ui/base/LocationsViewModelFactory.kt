package com.exceptioncatchers.rickandmortyapitry.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exceptioncatchers.rickandmortyapitry.api.ApiHelper
import com.exceptioncatchers.rickandmortyapitry.repostiory.Repository
import com.exceptioncatchers.rickandmortyapitry.ui.main.viewmodel.LocationsViewModel

@Suppress("UNCHECKED_CAST")
class LocationsViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationsViewModel::class.java)) {
            return LocationsViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}