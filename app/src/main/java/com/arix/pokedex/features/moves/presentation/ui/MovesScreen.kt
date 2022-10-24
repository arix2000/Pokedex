package com.arix.pokedex.features.moves.presentation.ui

import androidx.compose.runtime.Composable
import com.arix.pokedex.core.Constants.PokemonMovesScreen.MOVES_ITEM_LIMIT
import com.arix.pokedex.features.common.search_view.domain.SearchParams
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumn
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.presentation.MovesViewModel
import com.arix.pokedex.features.moves.presentation.ui.components.MoveListItem
import com.arix.pokedex.utils.Resource
import org.koin.androidx.compose.getViewModel

@Composable
fun MovesScreen(viewModel: MovesViewModel = getViewModel()) {
    val state = viewModel.state.value
    if (state.moveNames.isNotEmpty())
        MovesScreenContent(state) { viewModel.getMoveListFrom(it) }
}

@Composable
private fun MovesScreenContent(
    state: MovesScreenState,
    objectFromNames: suspend (List<String>) -> List<Resource<Move>>
) {
    SearchableLazyColumn(
        searchParams = SearchParams(state.moveNames, MOVES_ITEM_LIMIT, Move.EMPTY, objectFromNames),
        searchableContent = { moves ->
            items(moves.size) {
                MoveListItem(move = moves[it])
            }
        }
    )
}