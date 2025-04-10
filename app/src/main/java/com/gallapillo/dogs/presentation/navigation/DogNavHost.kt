package com.gallapillo.dogs.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.gallapillo.dogs.presentation.breeds.BreedScreen
import com.gallapillo.dogs.presentation.breeds.BreedsViewModel
import com.gallapillo.dogs.presentation.dogs.DogScreen
import com.gallapillo.dogs.presentation.dogs.DogsViewModel

@Composable
fun DogNavHost(
    imageLoader: ImageLoader,
    breedsViewModel: BreedsViewModel = viewModel(),
    dogsViewModel: DogsViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "breeds") {
        composable("breeds") {
            BreedScreen(
                navController = navController,
                breedsViewModel = breedsViewModel
            )
        }
        composable("dog/{breed_name}") { backStackEntry ->
            val breedName = backStackEntry.arguments?.getString("breed_name") ?: ""
            DogScreen(
                breedName = breedName,
                imageLoader = imageLoader,
                dogsViewModel = dogsViewModel
            )
        }
    }
}