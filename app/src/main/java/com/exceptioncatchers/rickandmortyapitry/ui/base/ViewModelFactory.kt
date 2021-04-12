package com.exceptioncatchers.rickandmortyapitry.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exceptioncatchers.rickandmortyapitry.api.ApiHelper
import com.exceptioncatchers.rickandmortyapitry.repostiory.Repository
import com.exceptioncatchers.rickandmortyapitry.ui.main.viewmodel.CharactersViewModel
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