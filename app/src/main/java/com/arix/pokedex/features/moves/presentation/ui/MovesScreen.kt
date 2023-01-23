package com.arix.pokedex.features.moves.presentation.ui

import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.core.Constants.PokemonMovesScreen.MOVES_ITEM_LIMIT
import com.arix.pokedex.features.common.search_view.domain.SearchParams
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumn
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.presentation.MovesViewModel
import com.arix.pokedex.features.moves.presentation.ui.components.MoveListItem
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.Resource
import org.koin.androidx.compose.getViewModel

@Composable
fun MovesScreen(
    viewModel: MovesViewModel = getViewModel(),
    navigateToMoveDetails: (String) -> Unit
) {
    val state = viewModel.state.value
    if (state.moveNames.isNotEmpty())
        MovesScreenContent(state, navigateToMoveDetails) { viewModel.getMoveListFrom(it) }
}

@Composable
private fun MovesScreenContent(
    state: MovesScreenState,
    navigateToMoveDetails: (String) -> Unit,
    objectFromNames: suspend (List<String>) -> List<Resource<Move>>
) {
    SearchableLazyColumn(
        searchParams = SearchParams(state.moveNames, MOVES_ITEM_LIMIT, Move.EMPTY, objectFromNames),
        searchableContent = { moves ->
            items(moves, key = { it.id }) { move ->
                MoveListItem(move) { moveId -> navigateToMoveDetails(moveId) }
            }
        }
    )
}

@Preview
@Composable
private fun MovesScreenPreview() {
    PokedexTheme {
        Surface {
            Text(text = "All previews for this view are in the SearchableLazyColumnPreviews.kt")
        }
    }
}