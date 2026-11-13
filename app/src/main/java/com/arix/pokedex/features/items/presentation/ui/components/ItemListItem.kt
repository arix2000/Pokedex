package com.arix.pokedex.features.items.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arix.pokedex.R
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
    val itemColoredCategory = item.category.mapToColoredCategory()
    var showImage by remember { mutableStateOf(item.sprites.default != null) }
    Box {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clip(Shapes.large)
                .clickableOnceInTime { onClick(item) }
                .background(getBrushBasedBy(itemColoredCategory.color))
                .padding(vertical = 10.dp, horizontal = 20.dp)) {
            if (showImage)
                AsyncImage(
                    model = item.sprites.default,
                    contentDescription = "Item image",
                    placeholder = painterResource(R.drawable.potion),
                    onError = { showImage = false },
                    modifier = Modifier.size(36.dp)
                )
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
                backgroundColor = itemColoredCategory.color,
            ) {
                Box(
                    Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                ) {
                    Text(text = itemColoredCategory.name, fontSize = FontSizes.normal)
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