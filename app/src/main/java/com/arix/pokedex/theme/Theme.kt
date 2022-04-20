package com.arix.pokedex.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Accent,
)

@Composable
fun PokedexTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}