package com.michael.actorlist.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michael.actorlist.di.Injection
import com.michael.actorlist.model.Actor
import com.michael.actorlist.ui.ViewModelFactory
import com.michael.actorlist.ui.common.UiState
import com.michael.actorlist.ui.components.ActorListItem
import com.michael.actorlist.ui.components.AppBarHome
import com.michael.actorlist.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
    navigateToProfile: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllActors()
            }
            is UiState.Success -> {
                HomeContent(
                    actor = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    navigateToProfile = navigateToProfile,
                    viewModel = viewModel
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    actor: List<Actor>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    navigateToProfile: () -> Unit,
    viewModel: HomeViewModel
) {
    val query by viewModel.query
    Column(
        modifier = modifier
    ) {
        AppBarHome(navigateToProfile)
        LazyColumn (
            modifier = modifier
        ){
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier
                )
            }
            items(actor, key = { it.id }) { data ->
                ActorListItem(
                    id = data.id,
                    name = data.name,
                    photoUrl = data.photo,
                    dateOfBirth = data.dateOfBirth,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                )
            }
        }
    }
}