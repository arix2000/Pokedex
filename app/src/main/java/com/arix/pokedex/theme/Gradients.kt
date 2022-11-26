package com.arix.pokedex.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object Gradients {
    //TODO move all app gradients here
    fun getBorderVerticalGradient(color: Color): Brush =
        Brush.verticalGradient(listOf(color, color, Color.Transparent, Color.Transparent))
}