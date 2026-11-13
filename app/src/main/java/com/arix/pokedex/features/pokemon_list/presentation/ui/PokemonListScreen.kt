package com.arix.pokedex.features.pokemon_list.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.extensions.gridItems
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.common.search_view.domain.SearchParams
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumn
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.features.pokemon_list.presentation.PokemonListViewModel
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.PokemonListItem
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.ApiResponse
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.w3c.dom.Text

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = getViewModel()
) {
    PokemonGrid(getPokemonList = { offset, searchQuery ->
        viewModel.getPokemonList(offset, searchQuery)
    })
}

@Composable
fun PokemonGrid(
    getPokemonList: suspend (offset: Int, searchQuery: String) -> ApiResponse<Page<PokemonItem>>,
    navigator: Navigator = get()
) {
    SearchableLazyColumn(SearchParams(getItemList = getPokemonList))
    { pokemonList ->
        gridItems(pokemonList, cells = 2) {
            PokemonListItem(
                pokemonItem = it,
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

