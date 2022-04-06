package com.arix.pokedex.features.poke_list.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun PokeListScreen() {

}

@Preview
@Composable
fun PokeListScreenPreview() {
    PokedexTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PokeListScreen()
        }
    }
}