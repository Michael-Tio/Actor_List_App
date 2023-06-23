package com.michael.actorlist.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.michael.actorlist.R
import com.michael.actorlist.di.Injection
import com.michael.actorlist.ui.ViewModelFactory
import com.michael.actorlist.ui.common.UiState
import com.michael.actorlist.ui.components.AppBarBack
import com.michael.actorlist.ui.theme.Shapes


@Composable
fun DetailScreen(
    actorId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getActorById(actorId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.name,
                    data.dateOfBirth,
                    data.placeOfBirth,
                    data.movies,
                    data.photo,
                    navigateBack = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    dateOfBirth: String,
    placeOfBirth: String,
    movies: String,
    photo: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        AppBarBack(stringResource(R.string.detail) ,navigateBack)
        Spacer(modifier = modifier.size(32.dp))
        AsyncImage(
            model = photo,
            contentDescription = stringResource(R.string.actors_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(200.dp)
                .clip(Shapes.medium)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = name,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = "$placeOfBirth, $dateOfBirth",
            fontSize = 18.sp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = stringResource(R.string.movies),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        Text(
            text = movies,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(horizontal = 16.dp)

        )
    }
}