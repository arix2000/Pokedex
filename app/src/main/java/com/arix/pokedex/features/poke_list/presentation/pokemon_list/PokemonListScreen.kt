package com.arix.pokedex.features.poke_list.presentation.pokemon_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.R
import com.arix.pokedex.extensions.gridItems
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.list.PokemonList
import com.arix.pokedex.features.poke_list.presentation.PokemonViewModel
import com.arix.pokedex.features.poke_list.presentation.pokemon_list.components.PokemonItem
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import com.arix.pokedex.views.ErrorScreenWithRetryButtonCondensed
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonViewModel = getViewModel()
) {
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
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        gridItems(state.pokemonList ?: emptyList(), cells = 2) {
            PokemonItem(pokemonDetails = it)
        }
        item {
            LaunchedEffect(true) {
                viewModel.getNextOrInitialPokemonList()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp),
                contentAlignment = Alignment.Center
            ) {
                if (state.errorMessage == null)
                    CircularProgressIndicator()
                else
                    ErrorScreenWithRetryButtonCondensed(
                        onRetryClicked = { viewModel.getNextOrInitialPokemonList() },
                        modifier = Modifier.fillMaxWidth()
                    )
            }
        }
    }
}