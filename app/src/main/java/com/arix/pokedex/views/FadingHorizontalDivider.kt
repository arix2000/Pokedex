package com.arix.pokedex.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun FadingHorizontalDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(0.7.dp)
            .padding(horizontal = 10.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Gray,
                        Color.Transparent
                    )
                )
            )
    )
}

@Preview
@Composable
fun FadingHorizontalDividerPreview() {
    PokedexTheme {
        Surface(modifier = Modifier.height(30.dp)) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                FadingHorizontalDivider()
            }
        }
    }
}