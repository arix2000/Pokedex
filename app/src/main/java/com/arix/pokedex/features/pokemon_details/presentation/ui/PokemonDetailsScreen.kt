package com.arix.pokedex.features.pokemon_details.presentation.ui

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.R
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonDetailsScreen(pokemonName: String, viewModel: PokemonDetailsViewModel = getViewModel()) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.invokeEvent(PokemonDetailsEvent.GetInitialData(pokemonName))
    }
    if (state.isLoading || state.pokemonDetails == null)
        DefaultProgressIndicatorScreen()
    else
        PokemonDetailsScreenContent(state.pokemonDetails)
}

@Composable
fun PokemonDetailsScreenContent(pokemonDetails: PokemonDetails) {

}

@Preview
@Composable
private fun PokemonDetailsScreenContentPreview() {
    val context = LocalContext.current
    val pokemonDetailsJson = remember {
        context.resources.openRawResource(R.raw.pokemon_details_example)
            .reader()
            .readText()
    }
    val pokemonDetails: PokemonDetails =
        remember { Gson().fromJson(pokemonDetailsJson, PokemonDetails::class.java) }

    PokedexTheme {
        Surface {
            PokemonDetailsScreenContent(pokemonDetails)
        }
    }
}