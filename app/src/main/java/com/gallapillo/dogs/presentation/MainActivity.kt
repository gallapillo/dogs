package com.gallapillo.dogs.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.gallapillo.dogs.presentation.breeds.BreedScreen
import com.gallapillo.dogs.presentation.breeds.BreedsViewModel
import com.gallapillo.dogs.presentation.dogs.DogScreen
import com.gallapillo.dogs.presentation.dogs.DogsViewModel
import com.gallapillo.dogs.presentation.navigation.DogNavHost
import com.gallapillo.dogs.presentation.theme.DogsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val breedsViewModel: BreedsViewModel by viewModels()
    private val dogsViewModel: DogsViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DogNavHost(
                        imageLoader = imageLoader,
                        dogsViewModel = dogsViewModel,
                        breedsViewModel = breedsViewModel
                    )
                }
            }
        }
    }
}