package com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain

import androidx.compose.foundation.horizontalScroll
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
import com.arix.pokedex.R
import com.arix.pokedex.extensions.hasOneItem
import com.arix.pokedex.extensions.isLastElement
import com.arix.pokedex.extensions.isNotFirstElement
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.presentation.ui.components.PokemonItem
import com.arix.pokedex.features.pokemon_details.domain.model.EvolutionStep
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsEvent
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.expandable_section.ExpandableSection
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.utils.drawVerticalScrollbar
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun EvolutionChainView(
    pokemonDetails: PokemonDetails,
    evolutionChain: PokemonEvolutionChain,
    navigateToPokemonDetails: (String) -> Unit,
    viewModel: PokemonDetailsViewModel = getViewModel()
) {
    val state = viewModel.evolutionSectionState.value
    LaunchedEffect(key1 = true) {
        if (state.pokemonEvolutionSteps == null)
            viewModel.invokeEvent(
                PokemonDetailsEvent.GetEvolutionPokemonDetailsList(evolutionChain.getPokemonInChainNames())
            )
    }

    when {
        state.isLoading -> DefaultProgressIndicatorScreen(
            Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        state.pokemonEvolutionSteps != null -> EvolutionChainContent(
            pokemonDetails,
            state.pokemonEvolutionSteps,
            navigateToPokemonDetails
        )
        state.errorMessage != null -> Text(text = stringResource(R.string.unexpected_error))
    }
}

@Composable
private fun EvolutionChainContent(
    parentPokemonDetails: PokemonDetails,
    pokemonEvolutionSteps: List<EvolutionStep>,
    navigateToPokemonDetails: (String) -> Unit
) {
    val commonScrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.Bottom
    ) {
        pokemonEvolutionSteps.forEach { evolutionStep ->
            val columnScrollState =
                if (evolutionStep.pokemonDetailList.hasOneItem()) rememberScrollState()
                else commonScrollState
            if (pokemonEvolutionSteps.isNotFirstElement(evolutionStep)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .padding(bottom = 115.dp)
                ) {
                    // TODO change to currently indicated by arrow
                    EvolutionRuleView(evolutionStep.pokemonEvolutionDetails.first())
                    Spacer(modifier = Modifier.height(5.dp))
                    ArrowToNextEvolution(parentPokemonDetails.types.first().getTypeColor())
                }
            }
            Column(
                modifier = Modifier
                    .height(250.dp)
                    .drawVerticalScrollbar(columnScrollState)
                    .verticalScroll(columnScrollState)
            ) {
                evolutionStep.pokemonDetailList.forEachIndexed { index, it ->
                    PokemonItem(
                        pokemonDetails = it,
                        modifier = Modifier
                            .width(155.dp)
                            .height(230.dp),
                        onClick = if (parentPokemonDetails.name != it.name) {
                            { navigateToPokemonDetails(it.name) }
                        } else null
                    )
                    if (evolutionStep.pokemonDetailList.isLastElement(it))
                        Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun EvolutionChainContentPreview() {
    PokedexTheme {
        Surface {
            val context = LocalContext.current
            val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
            ExpandableSection(title = "Evolution Chain", expandedInitially = true) {
                EvolutionChainContent(
                    pokemonDetails,
                    pokemonEvolutionSteps = listOf(
                        EvolutionStep(listOf(pokemonDetails), listOf()),
                        EvolutionStep(
                            listOf(pokemonDetails, pokemonDetails, pokemonDetails),
                            listOf()
                        )
                    )
                ) {}
            }
        }
    }
}
