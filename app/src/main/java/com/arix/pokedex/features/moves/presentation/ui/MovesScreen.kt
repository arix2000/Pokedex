package com.arix.pokedex.features.moves.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.arix.pokedex.features.moves.presentation.MovesViewModel
import com.arix.pokedex.features.moves.presentation.ui.components.MoveListItem
import com.arix.pokedex.features.poke_list.presentation.ui.components.SearchBar
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import com.arix.pokedex.views.ErrorScreenWithRetryButtonCondensed
import org.koin.androidx.compose.getViewModel

@Composable
fun MovesScreen(viewModel: MovesViewModel = getViewModel()) {
    val state = viewModel.state.value
    when {
        state.moves != null -> MovesScreenContent(state) { viewModel.invokeEvent(it) }
        state.isInitialLoading -> DefaultProgressIndicatorScreen()
        state.isErrorOnInitial() -> ErrorScreenWithRetryButton(
            modifier = Modifier.fillMaxSize(),
            onRetryClicked = { viewModel.invokeEvent(MovesScreenEvent.GetNextMoves) }
        )
    }
}

@Composable
private fun MovesScreenContent(state: MovesScreenState, invokeEvent: (MovesScreenEvent) -> Unit) {
    Column {
        SearchBar(
            onValueChange = {  },
            modifier = Modifier.padding(bottom = 6.dp, start = 10.dp, end = 10.dp)
        )
        MovesListView(state, invokeEvent)
    }
}

@Composable
private fun MovesListView(state: MovesScreenState, invokeEvent: (MovesScreenEvent) -> Unit) {
    if (state.moves != null)
        LazyColumn {
            items(state.moves.size) {
                MoveListItem(move = state.moves[it])
            }

            if (!state.isListEndReached)
                item {
                    if (state.moves.isNotEmpty())
                        LaunchedEffect(key1 = true) {
                            invokeEvent(MovesScreenEvent.GetNextMoves)
                        }
                    LoadingOrError(errorMessage = state.errorMessage, invokeEvent)
                }
        }
}

@Composable
private fun LoadingOrError(errorMessage: String?, invokeEvent: (MovesScreenEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        if (errorMessage != null)
            ErrorScreenWithRetryButtonCondensed(
                onRetryClicked = { invokeEvent(MovesScreenEvent.GetNextMoves) },
                modifier = Modifier.fillMaxWidth()
            )
        else
            CircularProgressIndicator()

    }
}

@Preview
@Composable
private fun MovesScreenPreview() {
    PokedexTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            val context = LocalContext.current
            val moves = remember {
                MockResourceReader(context).getPokemonMoveListMock()
            }
            MovesScreenContent(state = MovesScreenState(moves)) {}
        }
    }
}