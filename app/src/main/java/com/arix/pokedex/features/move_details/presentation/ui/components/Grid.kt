package com.arix.pokedex.features.move_details.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun <T> Grid(
    modifier: Modifier = Modifier,
    data: List<T>,
    cells: Int,
    item: @Composable BoxScope.(T) -> Unit
) {
    Column(modifier) {
        data.chunked(cells).forEach { row ->
            Row {
                row.forEach { itemData ->
                    Box(Modifier.weight(1f)) {
                        item(itemData)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun GridPreview() {
    PokedexTheme {
        Surface {
            Grid(
                data = listOf(
                    "test1", "test2", "test3", "test1", "test2",
                    "test3", "test1", "test2", "test3", "test1", "test2", "test3"
                ), cells = 3
            ) {
                Card(backgroundColor = Color.Green, modifier = Modifier.fillMaxWidth()) {
                    Text(text = it)
                }
            }
        }
    }
}