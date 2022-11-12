package com.arix.pokedex.features.common.search_view.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.extensions.gridItems
import com.arix.pokedex.features.moves.presentation.ui.components.MoveListItem
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.PokemonListItem
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader

@Preview
@Composable
private fun SearchableLazyColumnPokemonDetailsPreview() {
    PokedexTheme {
        Surface {
            val context = LocalContext.current
            val moves = remember {
                MockResourceReader(context).getPokemonMoveListMock()
            }
            SearchableLazyColumnContent(
                SearchableLazyColumnState(
                    items = moves,
                ), {
                    items(moves.size) {
                        MoveListItem(move = moves[it])
                    }
                }) {}
        }
    }
}

@Preview
@Composable
private fun SearchableLazyColumnMovesPreview() {
    PokedexTheme {
        Surface {
            val context = LocalContext.current
            val pokemonList = remember {
                val pokemon = MockResourceReader(context).getPokemonDetailsMock()
                listOf(pokemon, pokemon, pokemon, pokemon, pokemon, pokemon, pokemon, pokemon)
            }
            SearchableLazyColumnContent(
                SearchableLazyColumnState(
                    items = pokemonList,
                ), {
                    gridItems(pokemonList, 2) {
                        PokemonListItem(it)
                    }
                }) {}
        }
    }
}