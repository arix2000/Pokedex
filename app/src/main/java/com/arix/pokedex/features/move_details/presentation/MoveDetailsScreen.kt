package com.arix.pokedex.features.move_details.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun MoveDetailsScreen(moveId: Int) {
    Text(text = "TODO: $moveId")
}

@Preview
@Composable
private fun MoveDetailsScreenPreview() {
    PokedexTheme {
        Surface {

        }
    }
}