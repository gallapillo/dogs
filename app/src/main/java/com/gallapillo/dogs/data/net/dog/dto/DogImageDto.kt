package com.gallapillo.dogs.data.net.dog.dto

import kotlinx.serialization.Serializable

@Serializable
data class DogImageDto(
    val message: List<String>,
    val status: String
)