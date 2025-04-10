package com.gallapillo.dogs.domain.use_case

import com.gallapillo.dogs.data.DogsRepository
import javax.inject.Inject

class GetAllDogsImagesUseCase @Inject constructor(
    private val dogsRepository: DogsRepository
) {
    suspend operator fun invoke(breed: String) = dogsRepository.getDogImages(breed)
}