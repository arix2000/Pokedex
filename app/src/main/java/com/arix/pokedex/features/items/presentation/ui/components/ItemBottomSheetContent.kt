package com.arix.pokedex.features.items.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arix.pokedex.extensions.isPreview
import com.arix.pokedex.extensions.toSentenceCase
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.domain.model.item_details.ItemDetails
import com.arix.pokedex.features.items.presentation.ItemsViewModel
import com.arix.pokedex.features.items.presentation.ui.ItemEvent
import com.arix.pokedex.features.move_details.presentation.ui.components.BorderedTextTile
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.GrayA75
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import org.koin.androidx.compose.getViewModel

@Composable
fun ItemBottomSheetContent(item: Item, viewModel: ItemsViewModel = getViewModel()) {
    LaunchedEffect(key1 = item.id) {
        viewModel.invokeEvent(ItemEvent.GetItemDetails(item.id.toString()))
    }
    val state = viewModel.state.value
    when {
        state.isLoading && !isPreview() ->
            Box(modifier = Modifier.background(Color.Black)) {
                DefaultProgressIndicatorScreen(modifier = Modifier.fillMaxHeight(0.5f))
            }
        state.itemDetails != null -> ItemBottomSheetInnerContent(state.itemDetails)
        state.errorMessage != null -> ErrorScreenWithRetryButton {
            viewModel.invokeEvent(ItemEvent.GetItemDetails(item.id.toString()))
        }
    }
}

@Composable
private fun ItemBottomSheetInnerContent(item: ItemDetails) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 27.dp)
                    .background(GrayA75, shape = CircleShape)
                    .height(6.dp)
                    .width(27.dp)
            )
            Text(
                text = item.name.toSentenceCase(),
                fontSize = FontSizes.extraLarge,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 7.dp)
                    .background(item.categoryColor, shape = Shapes.large)
                    .padding(horizontal = 31.dp),
            ) {
                Text(text = item.categoryName)
            }
            ItemDetailsBaseInfoTile(item)
            Spacer(modifier = Modifier.height(38.dp))
            BorderedTextTile(
                text = getAnnotatedEffectString(item),
                borderColor = item.categoryColor,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(9.dp))
            BorderedTextTile(
                text = item.attributes.joinToString(),
                title = "Attributes",
                borderColor = item.categoryColor,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun getAnnotatedEffectString(item: ItemDetails): AnnotatedString {
    return buildAnnotatedString {
        append(AnnotatedString(item.effectTitle, SpanStyle(fontWeight = FontWeight.Bold)))
        append(" - ")
        append(item.effectText)
    }
}

@Preview
@Composable
private fun ItemBottomSheetContentPreview() {
    val context = LocalContext.current
    val item = remember { MockResourceReader(context).getPokemonItemMock() }
    PokedexTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            ItemBottomSheetInnerContent(item)
        }
    }
}