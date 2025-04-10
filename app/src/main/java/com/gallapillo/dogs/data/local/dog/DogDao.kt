package com.gallapillo.dogs.data.local.dog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {

    @Query("SELECT * FROM dog_entity WHERE :breed = breed")
    fun getDogsByBreed(breed: String): List<DogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertDog(dogEntity: DogEntity)
}