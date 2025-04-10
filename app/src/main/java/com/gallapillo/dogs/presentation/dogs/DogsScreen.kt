package com.gallapillo.dogs.presentation.dogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DogScreen(
    breedName: String,
    dogsViewModel: DogsViewModel = viewModel(),
    imageLoader: ImageLoader
) {
    LaunchedEffect(breedName) {
        dogsViewModel.getAllDogsImages(breedName)
    }
    val dogsImages by dogsViewModel.dogsFlow.collectAsState()


    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = breedName)
            }
        }
        items(dogsImages.size) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(dogsImages[it])
                        .build(),
                    contentDescription = null,
                    imageLoader = imageLoader,
                    modifier = Modifier.size(324.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}