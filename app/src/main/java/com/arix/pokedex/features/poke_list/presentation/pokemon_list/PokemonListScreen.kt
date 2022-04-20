package com.arix.pokedex.features.poke_list.presentation.pokemon_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arix.pokedex.R
import com.arix.pokedex.extensions.gridItems
import com.arix.pokedex.features.poke_list.presentation.PokemonViewModel
import com.arix.pokedex.features.poke_list.presentation.pokemon_list.components.PokemonItem
import com.arix.pokedex.features.poke_list.presentation.pokemon_list.components.SearchBar
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import com.arix.pokedex.views.ErrorScreenWithRetryButtonCondensed
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonViewModel = getViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.cachePokemonNames(context.resources.openRawResource(R.raw.pokamon_names))
    }
    val state = viewModel.state.value
    when {
        state.isInitialLoading -> DefaultProgressIndicatorScreen()
        state.isErrorOnInitial() -> ErrorScreenWithRetryButton(
            onRetryClicked = { viewModel.getNextOrInitialPokemonList() },
            modifier = Modifier.fillMaxSize()
        )
        else -> PokemonGridView(navController, viewModel)
    }
}

@Composable
private fun PokemonGridView(
    navController: NavController,
    viewModel: PokemonViewModel
) {
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            onValueChange = { viewModel.actualSearchQuery = it.toLowerCase(LocaleList.current) },
            modifier = Modifier.padding(bottom = 6.dp, start = 10.dp, end = 10.dp)
        )
        if (state.isSearchResultsEmpty) NoResultsView()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            gridItems(state.pokemonList ?: emptyList(), cells = 2) {
                PokemonItem(pokemonDetails = it)
            }
            if (!state.isListEndedReached)
                item {
                    if (!state.pokemonList.isNullOrEmpty())
                        LaunchedEffect(true) {
                            if (state.isSearching)
                                viewModel.getNextOrInitialSearchedList()
                            else
                                viewModel.getNextOrInitialPokemonList()
                        }
                    LoadingOrError(state, viewModel)
                }
        }
    }
}

@Composable
private fun LoadingOrError(state: PokemonListState, viewModel: PokemonViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state.errorMessage != null)
            ErrorScreenWithRetryButtonCondensed(
                onRetryClicked = { viewModel.getNextOrInitialPokemonList() },
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