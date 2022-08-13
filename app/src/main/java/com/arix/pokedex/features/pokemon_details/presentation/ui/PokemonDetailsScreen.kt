package com.arix.pokedex.features.pokemon_details.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.R
import com.arix.pokedex.extensions.hasOneItem
import com.arix.pokedex.extensions.isPreview
import com.arix.pokedex.features.common.AppTopBar
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_details.domain.model.species.Variety
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.details.DetailsView
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain.EvolutionChainView
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.expandable_section.ExpandableSection
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.forms.VariantsView
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.header.PokemonDetailsHeader
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonDetailsScreen(
    pokemonName: String,
    viewModel: PokemonDetailsViewModel = getViewModel(),
    navigateToPokemonDetails: (String) -> Unit
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.invokeEvent(PokemonDetailsEvent.GetInitialData(pokemonName))
    }
    if (state.isLoading)
        DefaultProgressIndicatorScreen()
    else if (state.pokemonDetails == null || state.species == null || state.evolutionChain == null)
        Text(text = "Error: ${state.errorMessage}")
    else
        PokemonDetailsScreenContent(
            state.pokemonDetails,
            state.species,
            state.evolutionChain,
            navigateToPokemonDetails
        )
}

@Composable
fun PokemonDetailsScreenContent(
    pokemonDetails: PokemonDetails,
    species: PokemonSpecies,
    evolutionChain: PokemonEvolutionChain,
    navigateToPokemonDetails: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            PokemonDetailsHeader(pokemonDetails)
            Spacer(modifier = Modifier.height(12.dp))
            ExpandableSection(
                title = stringResource(R.string.details_title),
                expandedInitially = true
            ) {
                DetailsView(pokemonDetails, species)
            }
            Spacer(modifier = Modifier.height(12.dp))
            ExpandableSection(title = stringResource(R.string.pokemon_description)) {
                Text(text = species.getDescription())
            }
            Spacer(modifier = Modifier.height(10.dp))
            EvolutionChainSection(
                pokemonDetails,
                evolutionChain,
                navigateToPokemonDetails
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (!(species.varieties.hasOneItem() && species.varieties.first().is_default)) {
                VarietiesSection(
                    pokemonDetails.name,
                    species.varieties,
                    navigateToPokemonDetails
                )
            }
        }
    }
}

@Composable
private fun EvolutionChainSection(
    pokemonDetails: PokemonDetails,
    evolutionChain: PokemonEvolutionChain,
    navigateToPokemonDetails: (String) -> Unit
) {
    ExpandableSection(
        title = stringResource(R.string.evolution_chain_title),
        expandedInitially = true
    ) {
        if (!isPreview())
            EvolutionChainView(
                pokemonDetails,
                evolutionChain,
                navigateToPokemonDetails
            )
        else DefaultProgressIndicatorScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@Composable
private fun VarietiesSection(
    rootPokemonDetailsName: String,
    varieties: List<Variety>,
    navigateToPokemonDetails: (String) -> Unit
) {
    ExpandableSection(
        title = stringResource(R.string.pokemon_details_variants)
    ) {
        if (!isPreview())
            VariantsView(
                rootPokemonDetailsName,
                varieties,
                navigateToPokemonDetails
            )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Preview
@Composable
private fun PokemonDetailsScreenContentPreview() {
    val context = LocalContext.current
    val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
    val pokemonSpecies = remember { MockResourceReader(context).getPokemonSpeciesMock() }
    val pokemonEvolutionChain =
        remember { MockResourceReader(context).getPokemonEvolutionChainMock() }
    PokedexTheme {
        Surface {
            Column {
                AppTopBar(
                    navController = rememberNavController(),
                    showBackButton = true
                )
                PokemonDetailsScreenContent(
                    pokemonDetails,
                    pokemonSpecies,
                    pokemonEvolutionChain
                ) { }
            }
        }
    }
}