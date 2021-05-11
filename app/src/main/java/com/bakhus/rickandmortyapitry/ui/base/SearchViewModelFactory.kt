package com.bakhus.rickandmortyapitry.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.locale.CharacterDatabase
import com.bakhus.rickandmortyapitry.repostiory.Repository
import com.bakhus.rickandmortyapitry.ui.main.viewmodel.SerachViewModel

class SearchViewModelFactory(private val apiHelper: ApiHelper, val db: CharacterDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SerachViewModel(Repository(apiHelper, db)) as T
    }
}