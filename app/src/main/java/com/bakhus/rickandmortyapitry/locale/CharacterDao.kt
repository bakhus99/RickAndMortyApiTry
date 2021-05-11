package com.bakhus.rickandmortyapitry.locale

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bakhus.rickandmortyapitry.models.Character


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(character: Character):Long

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): LiveData<List<Character>>

}