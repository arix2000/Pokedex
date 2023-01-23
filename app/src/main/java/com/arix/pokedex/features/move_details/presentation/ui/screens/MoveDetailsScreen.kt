package com.arix.pokedex.features.move_details.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.extensions.isPreview
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.move_details.presentation.MoveDetailsViewModel
import com.arix.pokedex.features.move_details.presentation.ui.MoveDetailsEvent
import com.arix.pokedex.features.move_details.presentation.ui.components.MoveDetailsHeader
import com.arix.pokedex.features.move_details.presentation.ui.components.MoveDetailsTiles
import com.arix.pokedex.features.move_details.presentation.ui.components.learnedByPokemon.LearnedByPokemonSection
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import com.arix.pokedex.views.FadingHorizontalDivider
import org.koin.androidx.compose.getViewModel

@Composable
fun MoveDetailsScreen(
    moveId: Int,
    navigateToPokemonDetails: (String) -> Unit,
    navigateToLearnedByPokemonList: (List<String>, String) -> Unit,
    viewModel: MoveDetailsViewModel = getViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.invokeEvent(MoveDetailsEvent.LoadMoveDetailsEvent(moveId))
    }

    val state = viewModel.state.value
    when {
        state.move != null -> {
            MoveDetailsScreenContent(
                move = state.move, navigateToPokemonDetails, navigateToLearnedByPokemonList
            )
        }
        state.isLoading -> DefaultProgressIndicatorScreen()
        state.errorMessage != null -> ErrorScreenWithRetryButton {
            viewModel.invokeEvent(MoveDetailsEvent.LoadMoveDetailsEvent(moveId))
        }
    }
}

@Composable
private fun MoveDetailsScreenContent(
    move: UiMove,
    navigateToPokemonDetails: (String) -> Unit,
    navigateToLearnedByPokemonList: (List<String>, String) -> Unit
) {

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        MoveDetailsHeader(move)
        FadingHorizontalDivider()
        MoveDetailsTiles(move)
        if (!isPreview())
            LearnedByPokemonSection(move.learned_by_pokemon,
                navigateToPokemonDetails,
                { navigateToLearnedByPokemonList(it, move.name) })
    }
}

@Preview
@Composable
private fun MoveDetailsScreenPreview() {
    val context = LocalContext.current
    val move = remember { MockResourceReader(context).getPokemonMoveMock() }
    PokedexTheme {
        Surface {
            MoveDetailsScreenContent(UiMove.fromMove(move), {}, { _, _ -> })
        }
    }
}