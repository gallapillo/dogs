package com.gallapillo.dogs.data.net.breed

import javax.inject.Inject

class BreedsRemoteDataSource @Inject constructor(
    private val breedApi: BreedApi
) {
    suspend fun getAllBreeds() = breedApi.getAllBreeds().message
}