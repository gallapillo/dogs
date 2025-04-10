package com.gallapillo.dogs.domain.use_case

import com.gallapillo.dogs.data.BreedRepository
import javax.inject.Inject

class GetAllBreedsUseCase @Inject constructor (
    private val repository: BreedRepository
) {
    suspend operator fun invoke() = repository.getAllBreeds()
}