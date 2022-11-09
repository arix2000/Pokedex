package com.arix.pokedex.core.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter

data class DrawerSpecs(
    val title: String,
    @DrawableRes val iconId: Int
)
