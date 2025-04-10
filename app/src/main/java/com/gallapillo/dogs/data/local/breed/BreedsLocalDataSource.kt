package com.gallapillo.dogs.data.local.breed

class BreedsLocalDataSource(
    private val breedDao: BreedDao
) {
    fun getAllBreeds() = breedDao.getAllBreeds()

    fun addBreed(breedEntity: BreedEntity) = breedDao.insertBreed(breedEntity)
}