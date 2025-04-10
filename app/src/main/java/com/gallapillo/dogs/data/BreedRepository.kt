package com.gallapillo.dogs.data

import android.util.Log
import com.gallapillo.dogs.data.local.breed.BreedEntity
import com.gallapillo.dogs.data.local.breed.BreedsLocalDataSource
import com.gallapillo.dogs.data.net.breed.BreedsRemoteDataSource
import javax.inject.Inject

class BreedRepository @Inject constructor(
    private val remoteDataSource: BreedsRemoteDataSource,
    private val breedDataSource: BreedsLocalDataSource
) {

    suspend fun getAllBreeds(): List<String> {
        try {
            val dogsMap = remoteDataSource.getAllBreeds()
            val lst = mutableListOf<String>()
            dogsMap.forEach { entry ->
                if (entry.value.isEmpty()) {
                    lst.add(entry.key)
                    breedDataSource.addBreed(BreedEntity(breed = entry.key))
                } else {
                    entry.value.forEach {
                        val str = "$it ${entry.key}"
                        lst.add(str)
                        breedDataSource.addBreed(BreedEntity(breed = str))
                    }
                }
            }

            return lst
        } catch (e : Exception) {
            Log.e(BreedRepository::class.java.simpleName, "Error while load breeds: ${e.localizedMessage}")
            return breedDataSource.getAllBreeds().map { it.breed }
        }
    }
}