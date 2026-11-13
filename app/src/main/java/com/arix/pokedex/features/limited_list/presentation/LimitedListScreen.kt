package com.arix.pokedex.features.limited_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.core.navigation.Navigator
import com.arix.pokedex.features.common.AppTopBar
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.common.search_view.domain.SearchParams
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumn
import com.arix.pokedex.features.limited_list.domain.LimitedListType
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.locations.presentation.ui.composables.LocationListItem
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.presentation.ui.components.MoveListItem
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.features.pokemon_list.presentation.ui.PokemonGrid
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.ApiResponse
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun LimitedListScreen(
    pokemonNames: List<String>,
    type: LimitedListType,
    viewModel: LearnedByPokemonFullListViewModel = getViewModel(),
) {
    when (type) {
        LimitedListType.POKEMONS -> PokemonList(getPokemonList = ({ offset, searchQuery ->
            viewModel.getPokemonList(offset, searchQuery, pokemonNames)
        }))

        LimitedListType.MOVES -> MovesList(getMoveList = { offset, searchQuery ->
            viewModel.getMoves(offset, searchQuery, pokemonNames)
        })

        LimitedListType.LOCATIONS -> LocationsList(getLocationList = { offset, searchQuery ->
            viewModel.getLocations(offset, searchQuery, pokemonNames)
        })
    }
}

@Composable
private fun PokemonList(
    getPokemonList: suspend (offset: Int, searchQuery: String) -> ApiResponse<Page<PokemonItem>>
) {
    PokemonGrid(getPokemonList = { offset, searchQuery ->
        getPokemonList(offset, searchQuery)
    })
}

@Composable
private fun MovesList(
    navigator: Navigator = get(),
    getMoveList: suspend (offset: Int, searchQuery: String) -> ApiResponse<Page<MoveItem>>
) {
    SearchableLazyColumn(
        searchParams = SearchParams(getMoveList),
        searchableContent = { moves ->
            items(moves, key = { it.id }) { move ->
                MoveListItem(
                    move,
                    modifier = Modifier.padding(5.dp)
                ) { moveId -> navigator.goToMoveDetails(moveId) }
            }
        }
    )
}

@Composable
private fun LocationsList(
    navigator: Navigator = get(),
    getLocationList: suspend (offset: Int, searchQuery: String) -> ApiResponse<Page<LocationItem>>
) {
    SearchableLazyColumn(
        searchParams = SearchParams(getLocationList),
        searchableContent = { locations ->
            items(locations, key = { it.id }) { location ->
                LocationListItem(location, modifier = Modifier.padding(5.dp)) { locationId -> navigator.goToLocationDetails(locationId.toString()) }
            }
        }
    )
}

@Preview
@Composable
private fun LearnedByPokemonFullListPreview() {
    PokedexTheme {
        Surface {
            Column {
                AppTopBar(
                    navController = rememberNavController(),
                    showBackButton = true,
                    scaffoldState = rememberScaffoldState(),
                    title = "Ember can be learned by:"
                )
            }
        }
    }
}