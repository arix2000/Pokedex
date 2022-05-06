package com.arix.pokedex.features.pokemon_details.presentation.ui.components.header

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BackgroundGradientSpecs(
    val height: Dp = 260.dp,
    val alpha: Float = 0.7f,
    val radius: Float = 520f
) {
    companion object {
        val DEFAULT = BackgroundGradientSpecs(260.dp, 0.7f, 520f)
    }
}