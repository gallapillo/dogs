package com.gallapillo.dogs.data.local.dog

import javax.inject.Inject

class DogLocalDataSource @Inject constructor(
    private val dao: DogDao
) {
    fun loadDogsByBreed(breed: String) = dao.getDogsByBreed(breed)

    fun upsertDog(dogEntity: DogEntity) {
        dao.upsertDog(dogEntity)
    }
}