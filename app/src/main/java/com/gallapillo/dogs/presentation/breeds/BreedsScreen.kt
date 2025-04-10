package com.gallapillo.dogs.presentation.breeds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun BreedScreen(breedsViewModel: BreedsViewModel = viewModel(), navController: NavController) {
    breedsViewModel.getAllBreeds()
    val breeds by breedsViewModel.breedsFlow.collectAsState()

    LazyColumn {
        items(breeds.size) {
            Text(
                text = breeds[it],
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        navController.navigate("dog/${breeds[it]}")
                    }
            )
        }
    }
}