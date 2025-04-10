package com.gallapillo.dogs.data.net.breed

import retrofit2.http.GET

interface BreedApi {
    @GET("/api/breeds/list/all")
    suspend fun getAllBreeds(): BreedsDto
}