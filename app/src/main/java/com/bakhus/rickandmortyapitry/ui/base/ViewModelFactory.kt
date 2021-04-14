package com.bakhus.rickandmortyapitry.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.repostiory.Repository
import com.bakhus.rickandmortyapitry.ui.main.viewmodel.CharactersViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      if(modelClass.isAssignableFrom(CharactersViewModel::class.java)){
        return CharactersViewModel(Repository(apiHelper)) as T
      }
        throw IllegalArgumentException("unknowm class name")
    }
}