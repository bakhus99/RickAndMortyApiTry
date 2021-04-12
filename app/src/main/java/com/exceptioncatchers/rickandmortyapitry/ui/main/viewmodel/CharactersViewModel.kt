package com.exceptioncatchers.rickandmortyapitry.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.rickandmortyapitry.models.Character
import com.exceptioncatchers.rickandmortyapitry.models.CharacterList
import com.exceptioncatchers.rickandmortyapitry.repostiory.Repository
import com.exceptioncatchers.rickandmortyapitry.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: Repository) : ViewModel() {

    var listCharacter = MutableLiveData<List<Character>>()
    //var character = MutableLiveData<CharacterList>()

//    fun getCharc(page: Int) {
//        viewModelScope.launch {
//            val character = repository.getCharacters(page)
//            listCharacter.value = character.results
//        }
//    }

    fun getCharacters(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getCharacters(page)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }


}