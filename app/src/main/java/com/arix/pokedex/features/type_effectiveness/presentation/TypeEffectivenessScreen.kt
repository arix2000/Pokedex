package com.arix.pokedex.features.type_effectiveness.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.type_effectiveness.presentation.ui.TypeEffectivenessState
import com.arix.pokedex.features.type_effectiveness.presentation.ui.TypeEffectivenessViewModel
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import org.koin.androidx.compose.getViewModel

@Composable
fun TypeEffectivenessScreen(viewModel: TypeEffectivenessViewModel = getViewModel()) {
    val state = viewModel.state
    when {
        state.typeEffectiveness.isNotEmpty() -> {
            TypeEffectivenessScreenContent(state)
        }

        state.isLoading -> DefaultProgressIndicatorScreen()
        state.errorMessage != null -> ErrorScreenWithRetryButton {
            viewModel.getTypes()
        }
    }
}

@Composable
private fun TypeEffectivenessScreenContent(
    state: TypeEffectivenessState
) {

}

@Preview
@Composable
private fun TypeEffectivenessScreenPreview() {
    PokedexTheme {
        TypeEffectivenessScreenContent(
            TypeEffectivenessState(
                typeEffectiveness = mockTypeEffectivenessList
            )
        )
    }
}