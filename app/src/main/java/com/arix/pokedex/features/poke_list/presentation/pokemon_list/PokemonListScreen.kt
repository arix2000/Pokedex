package com.arix.pokedex.features.poke_list.presentation.pokemon_list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.extensions.gridItems
import com.arix.pokedex.features.poke_list.presentation.PokemonViewModel
import com.arix.pokedex.features.poke_list.presentation.pokemon_list.components.PokemonItem
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonViewModel = getViewModel()
) {
    if (viewModel.state.value.isInitialLoading)
        DefaultProgressIndicatorScreen()
    else
        PokemonGridView(navController, viewModel)
}

@Composable
private fun PokemonGridView(navController: NavController, viewModel: PokemonViewModel) {
    val state = viewModel.state.value
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        gridItems(state.pokemonList ?: emptyList(), cells = 2) {
            PokemonItem(pokemonDetails = it)
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            LaunchedEffect(true) {
                viewModel.getNextOrInitialPokemonList()
            }
        }
    }
}

fun LazyListState.isScrolledToEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Preview
@Composable
fun PokeListScreenPreview() {
    PokedexTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PokemonListScreen(rememberNavController())
        }
    }
}