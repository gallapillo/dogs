package com.gallapillo.dogs.data.local.breed

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreed(breedEntity: BreedEntity)

    @Query("SELECT * FROM breed_entity")
    fun getAllBreeds(): List<BreedEntity>
}