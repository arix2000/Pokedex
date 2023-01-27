package com.arix.pokedex.features.pokemon_details.presentation.ui.components.forms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.features.common.ScrollHintMask
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.PokemonListItem
import com.arix.pokedex.features.pokemon_details.domain.model.species.Variety
import com.arix.pokedex.features.pokemon_details.presentation.PokemonDetailsViewModel
import com.arix.pokedex.features.pokemon_details.presentation.ui.PokemonDetailsEvent
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.utils.drawHorizontalScrollbar
import com.arix.pokedex.views.shimmer_effect.ShimmerAnimatedBox
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun VariantsView(
    rootPokemonDetailsName: String,
    varieties: List<Variety>,
    viewModel: PokemonDetailsViewModel = getViewModel()
) {
    val state = viewModel.varietiesSectionState.value
    LaunchedEffect(key1 = true) {
        if (state.pokemonVarietiesDetails == null)
            viewModel.invokeEvent(PokemonDetailsEvent.GetPokemonVarietiesDetailsList(varieties))
    }
    when {
        state.isLoading -> ShimmerAnimatedBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(vertical = 10.dp)
        )
        state.pokemonVarietiesDetails != null -> VariantsContentView(
            rootPokemonDetailsName,
            state.pokemonVarietiesDetails.filter { it.name != rootPokemonDetailsName },
        )
    }
}

@Composable
private fun VariantsContentView(
    rootPokemonDetailsName: String,
    pokemonDetails: List<PokemonDetails>,
    navigator: Navigator = get()

) {
    val lazyRowState = rememberLazyListState()
    var shouldShowScrollHint by remember { mutableStateOf(pokemonDetails.size > 2) }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        LazyRow(state = lazyRowState, modifier = Modifier.drawHorizontalScrollbar(lazyRowState)) {
            items(pokemonDetails.size) {
                PokemonListItem(pokemonDetails = pokemonDetails[it], modifier = Modifier
                    .width(155.dp)
                    .height(230.dp),
                    onClick = if (rootPokemonDetailsName != pokemonDetails[it].name) {
                        { navigator.goToPokemonDetails(pokemonDetails[it].name) }
                    } else null)
            }
        }
        ScrollHintMask(shouldShowScrollHint)
    }

    if (lazyRowState.isScrollInProgress && shouldShowScrollHint)
        shouldShowScrollHint = false
}

@Preview
@Composable
fun VariantsContentViewPreview() {
    PokedexTheme {
        val context = LocalContext.current
        val pokemonDetail = remember { MockResourceReader(context).getPokemonDetailsMock() }
        Surface {
            VariantsContentView(
                rootPokemonDetailsName = "Scyther",
                pokemonDetails = listOf(pokemonDetail, pokemonDetail, pokemonDetail, pokemonDetail),
                Navigator()
            )
        }
    }
}