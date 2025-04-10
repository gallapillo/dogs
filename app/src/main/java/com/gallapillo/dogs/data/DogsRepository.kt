package com.gallapillo.dogs.data

import com.gallapillo.dogs.data.local.dog.DogEntity
import com.gallapillo.dogs.data.local.dog.DogLocalDataSource
import com.gallapillo.dogs.data.net.dog.DogsRemoteDataSource
import javax.inject.Inject

class DogsRepository @Inject constructor(
    private val remoteDataSource: DogsRemoteDataSource,
    private val localDataSource: DogLocalDataSource
) {
    suspend fun getDogImages(breed: String): List<String> {
        return try {
            val breds = breed.split(" ")
            if (breds.size > 1) {
                val response = remoteDataSource.getDogImagesBySubBreed(
                    breedName = breds[1],
                    subBreedName = breds[0]
                ).message
                response.forEach {
                    localDataSource.upsertDog(DogEntity(imageUrl = it,breed = breed))
                }
                response
            } else {
                val response = remoteDataSource.getDogImagesByBreed(breed = breds[0]).message
                response.forEach {
                    localDataSource.upsertDog(DogEntity(imageUrl = it,breed = breed))
                }
                response
            }
        } catch (e: Exception) {
            localDataSource.loadDogsByBreed(breed = breed).map { it.imageUrl }
        }
    }
}