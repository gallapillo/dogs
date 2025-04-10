package com.gallapillo.dogs.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gallapillo.dogs.data.local.breed.BreedDao
import com.gallapillo.dogs.data.local.breed.BreedEntity
import com.gallapillo.dogs.data.local.dog.DogDao
import com.gallapillo.dogs.data.local.dog.DogEntity

@Database(entities = [BreedEntity::class, DogEntity::class], version = 1, exportSchema = false)
abstract class DogsDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
    abstract fun dogDao(): DogDao

    companion object {
        @Volatile
        private var INSTANCE: DogsDatabase? = null

        operator fun invoke(context: Context): DogsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogsDatabase::class.java,
                    "dogs_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}