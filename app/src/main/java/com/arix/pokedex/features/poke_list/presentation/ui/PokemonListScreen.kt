package com.arix.pokedex.features.poke_list.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.extensions.gridItems
import com.arix.pokedex.features.poke_list.presentation.PokemonListViewModel
import com.arix.pokedex.features.poke_list.presentation.ui.components.PokemonItem
import com.arix.pokedex.features.poke_list.presentation.ui.components.SearchBar
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import com.arix.pokedex.views.ErrorScreenWithRetryButtonCondensed
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = getViewModel(),
    navigateToPokemonDetails: (String) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.cachePokemonNames(context.resources.openRawResource(R.raw.pokamon_names))
    }
    val state = viewModel.state.value
    when {
        state.isInitialLoading -> DefaultProgressIndicatorScreen()
        state.isErrorOnInitial() -> ErrorScreenWithRetryButton(
            onRetryClicked = { viewModel.invokeEvent(PokemonListEvent.OnRetryClicked) },
            modifier = Modifier.fillMaxSize()
        )
        else -> PokemonGridView(viewModel, navigateToPokemonDetails)
    }
}

@Composable
private fun PokemonGridView(
    viewModel: PokemonListViewModel,
    navigateToPokemonDetails: (String) -> Unit
) {
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            onValueChange = { viewModel.invokeEvent(PokemonListEvent.SearchByQuery(it)) },
            modifier = Modifier.padding(bottom = 6.dp, start = 10.dp, end = 10.dp)
        )
        if (state.isSearchResultsEmpty) NoResultsView()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            gridItems(state.pokemonList ?: emptyList(), cells = 2) {
                PokemonItem(pokemonDetails = it) {
                    navigateToPokemonDetails(it.name)
                }
            }
            if (!state.isListEndedReached)
                item {
                    if (!state.pokemonList.isNullOrEmpty())
                        LaunchedEffect(true) {
                            viewModel.invokeEvent(PokemonListEvent.GetNextPage)
                        }
                    LoadingOrError(state, viewModel)
                }
        }
    }
}

@Composable
private fun LoadingOrError(state: PokemonListState, viewModel: PokemonListViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state.errorMessage != null)
            ErrorScreenWithRetryButtonCondensed(
                onRetryClicked = { viewModel.invokeEvent(PokemonListEvent.OnRetryClicked) },
                modifier = Modifier.fillMaxWidth()
            )
        else
            CircularProgressIndicator()

    }
}

@Composable
fun NoResultsView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No results :(")
    }
}