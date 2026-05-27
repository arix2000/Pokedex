package com.arix.pokedex.features.type_effectiveness.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.type_effectiveness.presentation.ui.TypeEffectivenessState
import com.arix.pokedex.features.type_effectiveness.presentation.ui.TypeEffectivenessViewModel
import com.arix.pokedex.features.type_effectiveness.presentation.ui.components.SelectableTypesList
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
    /** TODO plan:
     * We have grid of selectable types, it will look like [com.arix.pokedex.features.pokemon_list.presentation.ui.components.TypeItem]
     * but will be selectable so we need to make another composable for it.
     * user picks up to 2 types, then we show him (dynamically as he clicks) types grouped by effectiveness
     * with effectiveness multiplier on its left.
     * **/
    Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
        SelectableTypesList(state.typeEffectiveness.map { it.type })
    }
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