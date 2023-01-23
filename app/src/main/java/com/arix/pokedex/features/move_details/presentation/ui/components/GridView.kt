package com.arix.pokedex.features.move_details.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.PrimaryDark

@Composable
fun <T> GridView(
    modifier: Modifier = Modifier,
    data: List<T>,
    cells: Int,
    alwaysFillSpace: Boolean = false,
    item: @Composable BoxScope.(T) -> Unit
) {
    Column(modifier) {
        data.chunked(cells).forEach { row ->
            Row {
                if (alwaysFillSpace)
                    row.forEach { itemData ->
                        Box(Modifier.weight(1f)) {
                            item(itemData)
                        }
                    }
                else
                    row.forEachIndexed { index, itemData ->
                        Box(Modifier.fillMaxWidth(1f / (cells - index))) {
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
            GridView(
                data = listOf(
                    "test1", "test2", "test3", "test4", "test5", "test6",
                    "test7", "test8", "test9", "test10", "test11"
                ), cells = 3
            ) {
                Card(
                    backgroundColor = PrimaryDark,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(text = it, textAlign = TextAlign.Center, color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
private fun GridAlwaysFillSpacePreview() {
    PokedexTheme {
        Surface {
            GridView(
                data = listOf(
                    "test1", "test2", "test3", "test4", "test5", "test6",
                    "test7", "test8", "test9", "test10", "test11"
                ), cells = 3, alwaysFillSpace = true
            ) {
                Card(
                    backgroundColor = PrimaryDark,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(text = it, textAlign = TextAlign.Center, color = Color.White)
                }
            }
        }
    }
}