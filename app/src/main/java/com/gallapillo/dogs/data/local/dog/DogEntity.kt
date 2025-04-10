package com.gallapillo.dogs.data.local.dog

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_entity")
data class DogEntity(
    @PrimaryKey(autoGenerate = false) val imageUrl: String,
    val breed: String
)