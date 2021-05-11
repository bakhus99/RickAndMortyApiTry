package com.bakhus.rickandmortyapitry.locale

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bakhus.rickandmortyapitry.models.Character


@Database(entities = [Character::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {

        @Volatile
        private var instance: CharacterDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CharacterDatabase::class.java,
                "character_db.db"
            ).build()


//        @Volatile
//        private var INSTANCE: CharacterDatabase? = null
//
//        fun getDatabase(context: Context): CharacterDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    CharacterDatabase::class.java,
//                    "character_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }

    }

}