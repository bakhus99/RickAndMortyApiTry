package com.exceptioncatchers.rickandmortyapitry.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exceptioncatchers.rickandmortyapitry.api.ApiHelper
import com.exceptioncatchers.rickandmortyapitry.repostiory.Repository
import com.exceptioncatchers.rickandmortyapitry.ui.main.viewmodel.SerachViewModel

class SearchViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SerachViewModel(Repository(apiHelper)) as T
    }
}