package com.arix.pokedex.features.moves.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.extensions.clickableOnceInTime
import com.arix.pokedex.extensions.toSentenceCase
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.TypeItem
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.utils.MockResourceReader

@Composable
fun MoveListItem(move: UiMove, onClick: (moveId: String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(Shapes.large)
            .clickableOnceInTime { onClick(move.id.toString()) }
            .background(getBrushBasedBy(move.type))
            .padding(vertical = 14.dp, horizontal = 20.dp)
    ) {
        Text(
            text = move.name.toSentenceCase(),
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = FontSizes.large,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Box {
            Surface(elevation = 2.dp, shape = Shapes.large, color = Color.Transparent) {
                TypeItem(type = move.type, itemFontSize = FontSizes.minimum)
            }
        }
    }
}

private fun getBrushBasedBy(type: Type): Brush {
    return Brush.horizontalGradient(
        colors = listOf(
            Color.Black,
            type.getTypeColor(0.3f),
        )
    )
}

@Preview
@Composable
fun MoveListItemPreview() {
    PokedexTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            val context = LocalContext.current
            val move = MockResourceReader(context).getPokemonMoveMock()
            MoveListItem(move) {}
        }
    }
}
