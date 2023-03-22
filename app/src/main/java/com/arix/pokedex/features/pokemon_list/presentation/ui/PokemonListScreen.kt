package com.arix.pokedex.features.pokemon_list.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.core.Constants.PokemonListScreen.POKEMON_LIST_ITEM_LIMIT
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.extensions.gridItems
import com.arix.pokedex.features.common.search_view.domain.SearchParams
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumn
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.presentation.PokemonListViewModel
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.PokemonListItem
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.ApiResponse
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = getViewModel()
) {
    val state = viewModel.state.value
    if (state.pokemonNames != null)
        PokemonGrid(state.pokemonNames, namesToPokemonResponses = {
            viewModel.getPokemonListFrom(it)
        })
}

@Composable
fun PokemonGrid(
    pokemonNames: List<String>,
    namesToPokemonResponses: suspend (List<String>) -> List<ApiResponse<PokemonDetails>>,
    navigator: Navigator = get()
) {
    SearchableLazyColumn(
        SearchParams(
            itemNames = pokemonNames,
            itemsLimit = POKEMON_LIST_ITEM_LIMIT,
            emptyItem = PokemonDetails.EMPTY,
            objectsFromNames = namesToPokemonResponses
        )
    ) { pokemonList ->
        gridItems(pokemonList, cells = 2) {
            PokemonListItem(
                pokemonDetails = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                navigator.goToPokemonDetails(it.name)
            }
        }
    }
}

@Preview
@Composable
private fun PokemonListPreview() {
    PokedexTheme {
        Surface {
            Text(text = "All previews for this view are in SearchableLazyColumnPreviews.kt")
        }
    }
}
