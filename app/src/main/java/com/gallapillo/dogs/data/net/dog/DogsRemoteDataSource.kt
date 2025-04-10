package com.gallapillo.dogs.data.net.dog

import javax.inject.Inject

class DogsRemoteDataSource @Inject constructor(
    private val dogApi: DogApi
) {
    suspend fun getDogImagesByBreed(breed: String) = dogApi.getAllDogsImageUrls(breed)

    suspend fun getDogImagesBySubBreed(breedName: String, subBreedName: String) =
        dogApi.getAllSubBreedDogsImageUrls(breedName, subBreedName)

}