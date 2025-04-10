package com.gallapillo.dogs.data.net.breed

import kotlinx.serialization.Serializable

@Serializable
data class BreedsDto(
    val message: Map<String, List<String>>,
    val status: String
)