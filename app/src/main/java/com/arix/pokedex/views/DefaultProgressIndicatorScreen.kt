package com.arix.pokedex.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun DefaultProgressIndicatorScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun DefaultProgressIndicatorScreenPreview() {
    PokedexTheme {
        Surface {
            DefaultProgressIndicatorScreen()
        }
    }
}