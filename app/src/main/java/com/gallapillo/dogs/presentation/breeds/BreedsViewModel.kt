package com.gallapillo.dogs.presentation.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.dogs.domain.use_case.GetAllBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) : ViewModel() {
    private val _breedsFlow: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val breedsFlow = _breedsFlow.asStateFlow()


    fun getAllBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            val dogs = getAllBreedsUseCase()
            _breedsFlow.value = dogs
        }
    }
}