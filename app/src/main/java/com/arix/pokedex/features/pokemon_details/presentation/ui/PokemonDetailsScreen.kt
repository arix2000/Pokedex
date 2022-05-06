package com.arix.pokedex.features.pokemon_details.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.features.common.AppTopBar
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.header.PokemonDetailsHeader
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonDetailsScreen(pokemonName: String, viewModel: PokemonDetailsViewModel = getViewModel()) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.invokeEvent(PokemonDetailsEvent.GetInitialData(pokemonName))
    }
    if (state.isLoading || state.pokemonDetails == null || state.species == null)
        DefaultProgressIndicatorScreen()
    else
        PokemonDetailsScreenContent(state.pokemonDetails, state.species)
}

@Composable
fun PokemonDetailsScreenContent(pokemonDetails: PokemonDetails, species: PokemonSpecies) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokemonDetailsHeader(pokemonDetails)
            Text(
                text = species.getDescription(),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PokemonDetailsScreenContentPreview() {
    val context = LocalContext.current
    val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
    val pokemonSpecies = remember { MockResourceReader(context).getPokemonSpeciesMock() }
    PokedexTheme {
        Surface {
            Column {
                AppTopBar(
                    navController = rememberNavController(),
                    showBackButton = true
                )
                PokemonDetailsScreenContent(pokemonDetails, pokemonSpecies)
            }
        }
    }
}