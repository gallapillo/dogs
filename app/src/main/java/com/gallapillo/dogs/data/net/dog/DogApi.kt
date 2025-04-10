package com.gallapillo.dogs.data.net.dog

import com.gallapillo.dogs.data.net.dog.dto.DogImageDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("/api/breed/{breed_name}/images")
    suspend fun getAllDogsImageUrls(@Path("breed_name") breedName: String): DogImageDto

    @GET("/api/breed/{breed_name}/{sub_breed_name}/images")
    suspend fun getAllSubBreedDogsImageUrls(
        @Path("breed_name") breedName: String,
        @Path("sub_breed_name") subBreedName: String
    ): DogImageDto
}