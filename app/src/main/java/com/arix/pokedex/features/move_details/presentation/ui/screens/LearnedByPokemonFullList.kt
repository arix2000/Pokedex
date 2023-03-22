package com.arix.pokedex.features.move_details.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.features.common.AppTopBar
import com.arix.pokedex.features.pokemon_list.presentation.ui.PokemonGrid
import com.arix.pokedex.theme.PokedexTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun LearnedByPokemonFullList(
    pokemonNames: List<String>,
    viewModel: LearnedByPokemonFullListViewModel = getViewModel(),
) {
    PokemonGrid(pokemonNames, namesToPokemonResponses = {
        viewModel.getPokemonListFrom(it)
    })
}

@Preview
@Composable
private fun LearnedByPokemonFullListPreview() {
    PokedexTheme {
        Surface {
            Column {
                AppTopBar(
                    navController = rememberNavController(),
                    showBackButton = true,
                    scaffoldState = rememberScaffoldState(),
                    title = "Ember can be learned by:"
                )
            }
        }
    }
}