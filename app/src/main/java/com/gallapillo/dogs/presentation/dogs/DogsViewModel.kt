package com.gallapillo.dogs.presentation.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.dogs.domain.use_case.GetAllDogsImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val getAllDogsImagesUseCase: GetAllDogsImagesUseCase
) : ViewModel() {
    private val _dogsFlow: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val dogsFlow = _dogsFlow.asStateFlow()

    fun getAllDogsImages(breed: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dogs = getAllDogsImagesUseCase(breed = breed)
            _dogsFlow.value = dogs
        }
    }
}