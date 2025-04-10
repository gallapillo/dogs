package com.gallapillo.dogs.data.local.breed

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_entity")
data class BreedEntity(
     @PrimaryKey(autoGenerate = false) val breed: String
)