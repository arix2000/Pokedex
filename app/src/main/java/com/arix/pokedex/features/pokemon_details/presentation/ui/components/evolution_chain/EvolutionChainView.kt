package com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.extensions.hasOneItem
import com.arix.pokedex.extensions.isLastElement
import com.arix.pokedex.extensions.isNotFirstElement
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.presentation.ui.components.PokemonListItem
import com.arix.pokedex.features.pokemon_details.domain.model.EvolutionStep
import com.arix.pokedex.features.pokemon_details.domain.model.PokemonEvolutionDetails
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsEvent
import com.arix.pokedex.features.pokemon_details.presentation.ui.components.expandable_section.ExpandableSection
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.utils.drawVerticalScrollbar
import com.arix.pokedex.views.shimmer_effect.ShimmerAnimatedBox
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
                PokemonDetailsEvent.GetEvolutionPokemonDetailsList(evolutionChain.getPokemonEvolutionSteps())
            )
    }

    when {
        state.isLoading -> ShimmerAnimatedBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(vertical = 10.dp)
        )
        state.pokemonEvolutionSteps != null -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            EvolutionChainContent(
                pokemonDetails,
                state.pokemonEvolutionSteps,
                navigateToPokemonDetails
            )
        }
        state.errorMessage != null -> Text(text = stringResource(R.string.unexpected_error))
    }
}

@Composable
private fun EvolutionChainContent(
    rootPokemonDetails: PokemonDetails,
    pokemonEvolutionSteps: List<EvolutionStep>,
    navigateToPokemonDetails: (String) -> Unit
) {
    val commonScrollState = rememberScrollState()
    var positionArrow = remember { 0f }
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.Bottom,
    ) {
        pokemonEvolutionSteps.forEach { evolutionStep ->
            val currentlyIndicated: MutableState<List<EvolutionDetail>?> =
                remember { mutableStateOf(evolutionStep.pokemonEvolutionDetails.firstOrNull()?.evolutionDetails) }
            val columnScrollState =
                if (evolutionStep.pokemonEvolutionDetails.hasOneItem()) rememberScrollState()
                else commonScrollState
            if (pokemonEvolutionSteps.isNotFirstElement(evolutionStep)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.padding(bottom = 115.dp)
                ) {
                    EvolutionRuleView(currentlyIndicated.value!!)
                    Spacer(modifier = Modifier.height(5.dp))
                    ArrowToNextEvolution(
                        rootPokemonDetails.types.first().getTypeColor(),
                        modifier = Modifier.onGloballyPositioned { coordinates ->
                            positionArrow = coordinates.positionInRoot().y
                        })
                }
            }
            Column(
                modifier = Modifier
                    .height(250.dp)
                    .drawVerticalScrollbar(columnScrollState)
                    .verticalScroll(columnScrollState)
            ) {
                evolutionStep.pokemonEvolutionDetails.forEachIndexed { index, it ->
                    Spacer(modifier = Modifier.onGloballyPositioned { coordinates ->
                        with(evolutionStep.pokemonEvolutionDetails) {
                            val positionTopOfItem = coordinates.positionInRoot().y
                            if (isNotEmpty()) {
                                onItemTopPositionChanged(
                                    positionTopOfItem,
                                    positionArrow,
                                    this[index].evolutionDetails,
                                    currentlyIndicated
                                )
                            }
                        }
                    })
                    PokemonListItem(
                        pokemonDetails = it.pokemonDetails,
                        modifier = Modifier
                            .width(155.dp)
                            .height(230.dp),
                        onClick = if (rootPokemonDetails.name != it.pokemonDetails.name) {
                            { navigateToPokemonDetails(it.pokemonDetails.name) }
                        } else null
                    )
                    if (evolutionStep.pokemonEvolutionDetails.isLastElement(it))
                        Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

private fun onItemTopPositionChanged(
    positionTopOfItem: Float,
    positionArrow: Float,
    evolutionDetails: List<EvolutionDetail>,
    currentlyIndicated: MutableState<List<EvolutionDetail>?>
) {
    currentlyIndicated.run {
        if (positionTopOfItem in positionArrow - 720..positionArrow && value != evolutionDetails)
            value = evolutionDetails
    }
}

@Preview
@Composable
fun EvolutionChainContentPreview() {
    PokedexTheme {
        Surface {
            val context = LocalContext.current
            val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
            val evolutionChain =
                remember { MockResourceReader(context).getPokemonEvolutionChainMock() }
            ExpandableSection(title = "Evolution Chain", expandedInitially = true) {
                EvolutionChainContent(
                    pokemonDetails,
                    pokemonEvolutionSteps = listOf(
                        EvolutionStep(
                            listOf(
                                PokemonEvolutionDetails(
                                    pokemonDetails,
                                    evolutionChain.chain.evolution_details
                                )
                            )
                        ),
                        EvolutionStep(
                            listOf(
                                PokemonEvolutionDetails(
                                    pokemonDetails,
                                    evolutionChain.chain.evolves_to.first().evolution_details
                                )
                            )
                        )
                    )
                ) {}
            }
        }
    }
}
