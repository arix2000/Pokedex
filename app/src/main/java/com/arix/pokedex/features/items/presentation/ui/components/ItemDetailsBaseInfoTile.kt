package com.arix.pokedex.features.items.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.features.common.text.ExpandableText
import com.arix.pokedex.features.items.domain.model.move_details.ItemDetails
import com.arix.pokedex.theme.Gradients
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.utils.MockResourceReader

@Composable
fun ItemDetailsBaseInfoTile(item: ItemDetails, isLoading: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .border(
                0.5.dp, Gradients.getBorderTwoSidesGradient(item.categoryColor), Shapes.large
            )
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "Item image",
                placeholder = painterResource(R.drawable.potion),
                onLoading = { isLoading.value = true },
                onSuccess = { isLoading.value = false },
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            ExpandableText(text = item.flavorText, maxLines = 2, textAlign = TextAlign.Start)
        }
    }
}

@Preview
@Composable
private fun ItemDetailsBaseInfoTilePreview() {
    val context = LocalContext.current
    val item = remember { MockResourceReader(context).getPokemonItemMock() }
    PokedexTheme {
        Surface {
            Box(Modifier.padding(5.dp)) {
                ItemDetailsBaseInfoTile(item, remember { mutableStateOf(false) })
            }
        }
    }
}