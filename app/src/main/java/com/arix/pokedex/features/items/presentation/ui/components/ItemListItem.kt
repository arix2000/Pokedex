package com.arix.pokedex.features.items.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.utils.MockResourceReader

@Composable
fun ItemListItem(
    item: Item,
    onClick: (item: Item) -> Unit,
) {
    val itemCategoryColor = item.category.mapCategoryToColoredCategory()
    Box {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clip(Shapes.large)
                .clickableOnceInTime { onClick(item) }
                .background(getBrushBasedBy(itemCategoryColor.color))
                .padding(vertical = 14.dp, horizontal = 20.dp)) {
            Text(
                text = item.name.toSentenceCase(),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = FontSizes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Card(
                shape = Shapes.large,
                backgroundColor = itemCategoryColor.color,
            ) {
                Box(
                    Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                ) {
                    Text(text = item.category.name)
                }
            }
        }
    }
}

private fun getBrushBasedBy(color: Color): Brush {
    return Brush.horizontalGradient(
        colors = listOf(
            Color.Black,
            color.copy(alpha = 0.3f),
        )
    )
}

@Preview
@Composable
private fun ItemListItemPreview() {
    val context = LocalContext.current
    val item = remember {
        MockResourceReader(context).getPokemonItemMock()
    }
    PokedexTheme {
        Surface {
            ItemListItem(item) {}
        }
    }
}