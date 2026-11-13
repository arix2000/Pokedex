package com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.extensions.ScanEvolutionDetails
import com.arix.pokedex.extensions.getIdFromUrl
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.Item
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.GrayA75

@Composable
fun EvolutionRuleView(evolutionDetails: List<EvolutionDetail>, onItemClicked: (Int) -> Unit) {
    evolutionDetails.ScanEvolutionDetails(
        evolveByLevel = {
            EvolutionRuleText(text = stringResource(id = R.string.evolution_level, it))
        },
        evolveByItem = {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        onItemClicked(it.url.getIdFromUrl())
                    }) {
                ItemAsyncImage(item = it)
            }
        },
        evolveByHoldingItem = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .width(110.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        onItemClicked(it.url.getIdFromUrl())
                    }
            ) {
                EvolutionRuleText(
                    text = stringResource(id = R.string.evolution_holding),
                    specifyWidth = true
                )
                ItemAsyncImage(item = it)
            }
        },
        evolveByHappiness = {
            EvolutionRuleText(text = stringResource(id = R.string.evolution_happiness, it))
        },
        evolveByTimeOfDay = {
            Icon(
                painter = painterResource(id = if (it == "day") R.drawable.ic_day else R.drawable.ic_night),
                contentDescription = stringResource(
                    id = if (it == "day") R.string.evolution_description_day else R.string.evolution_description_night
                )
            )
        },
        evolveByTrade = {
            Icon(
                painter = painterResource(id = R.drawable.ic_trade),
                contentDescription = stringResource(id = R.string.evolution_description_trade),
                modifier = Modifier.size(24.dp)
            )
        },
        evolveByKnownMove = {
            EvolutionRuleText(text = stringResource(id = R.string.evolution_known_move, it))
        },
        evolveByKnownMoveType = {
            Column {
                EvolutionRuleText(text = buildKnownMoveTypeAnnotatedString(it))
            }
        }
    )
}

@Composable
private fun EvolutionRuleText(text: String, specifyWidth: Boolean = false) {
    Text(
        text = text,
        fontSize = FontSizes.small,
        color = GrayA75,
        softWrap = true,
        modifier = if (specifyWidth) Modifier else Modifier.width(110.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun EvolutionRuleText(text: AnnotatedString) {
    Text(
        text = text,
        fontSize = FontSizes.small,
        color = GrayA75,
        softWrap = true,
        modifier = Modifier.width(110.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun buildKnownMoveTypeAnnotatedString(type: Type): AnnotatedString {
    val textStart = stringResource(id = R.string.evolution_known_move_type_start) + " "
    val textEnd = stringResource(id = R.string.evolution_known_move_type_end) + " "

    return buildAnnotatedString {
        append(textStart)
        append(
            AnnotatedString(
                type.name,
                SpanStyle(
                    color = Color.White,
                    background = type.getTypeColor(),
                    fontStyle = FontStyle.Italic
                )
            )
        )
        append(textEnd)
    }
}

@Composable
private fun ItemAsyncImage(item: Item) {
    var isError by remember { mutableStateOf(false) }
    if (isError)
        EvolutionRuleText(text = item.name)
    else
        AsyncImage(
            model = item.getImageUrl(),
            contentDescription = item.name,
            contentScale = ContentScale.FillWidth,
            onError = { isError = true },
            onSuccess = { isError = false },
            modifier = Modifier.width(28.dp),
        )
}