package com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain

import android.util.Log
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
import com.arix.pokedex.features.poke_list.presentation.ui.components.PokemonItem
import com.arix.pokedex.features.pokemon_details.domain.model.EvolutionStep
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail
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
    rootPokemonDetails: PokemonDetails,
    pokemonEvolutionSteps: List<EvolutionStep>,
    navigateToPokemonDetails: (String) -> Unit
) {
    val commonScrollState = rememberScrollState()
    var positionArrow = remember { 0f }
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.Bottom
    ) {
        pokemonEvolutionSteps.forEach { evolutionStep ->
            val currentlyIndicated: MutableState<EvolutionDetail?> =
                remember { mutableStateOf(evolutionStep.pokemonEvolutionDetails.firstOrNull() ) }
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
                evolutionStep.pokemonDetailList.forEachIndexed { index, it ->
                    Spacer(modifier = Modifier.onGloballyPositioned { coordinates ->
                        with(evolutionStep.pokemonEvolutionDetails) {
                            val positionTopOfItem = coordinates.positionInRoot().y
                            if (isNotEmpty()) {
                                onItemTopPositionChanged(
                                    positionTopOfItem,
                                    positionArrow,
                                    this[index],
                                    currentlyIndicated
                                )
                            }
                        }
                    })
                    PokemonItem(
                        pokemonDetails = it,
                        modifier = Modifier
                            .width(155.dp)
                            .height(230.dp),
                        onClick = if (rootPokemonDetails.name != it.name) {
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

private fun onItemTopPositionChanged(
    positionTopOfItem: Float,
    positionArrow: Float,
    evolutionDetail: EvolutionDetail,
    currentlyIndicated: MutableState<EvolutionDetail?>
) {
    currentlyIndicated.run {
        if (positionTopOfItem in positionArrow - 720..positionArrow && value != evolutionDetail) {
            value = value?.copy(evolutionDetail)
            Log.d("POSITION_OF_RANGE", "${evolutionDetail.item?.name}")
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
