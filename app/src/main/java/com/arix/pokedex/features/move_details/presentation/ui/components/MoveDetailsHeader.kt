package com.arix.pokedex.features.move_details.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.TypeItem
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.utils.MockResourceReader

@Composable
fun MoveDetailsHeader(move: UiMove) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.height(20.dp))
        Text(text = move.name, fontSize = FontSizes.extraLarge, textAlign = TextAlign.Center)
        Spacer(Modifier.height(3.dp))
        TypeItem(type = move.type, itemFontSize = FontSizes.medium)
        Spacer(Modifier.height(10.dp))
    }
}

@Preview
@Composable
private fun MoveDetailsHeaderPreview() {
    val context = LocalContext.current
    val move = remember { MockResourceReader(context).getPokemonMoveMock() }
    PokedexTheme {
        Surface {
            MoveDetailsHeader(UiMove.fromMove(move))
        }
    }
}