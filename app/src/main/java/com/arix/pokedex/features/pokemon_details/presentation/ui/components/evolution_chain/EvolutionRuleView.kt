package com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.extensions.ScanEvolutionDetails
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail
import com.arix.pokedex.theme.GrayA50
import com.arix.pokedex.theme.TextSize

@Composable
fun EvolutionRuleView(evolutionDetails: List<EvolutionDetail>) {
    evolutionDetails.ScanEvolutionDetails(
        evolveByLevel = {
            EvolutionRuleText(text = "Level $it")
        },
        evolveByItem = {
            AsyncImage(
                model = it.getImageUrl(),
                contentDescription = it.name,
                contentScale = ContentScale.FillWidth,
                // TODO give item name on error (sometimes resource is missing)
                error = painterResource(id = R.drawable.pokemon_not_found_image),
                modifier = Modifier.width(28.dp),
            )
        },
        evolveByHoldingItem = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(110.dp)
            ) {
                EvolutionRuleText(text = "Holding:", specifyWidth = true)
                AsyncImage(
                    model = it.getImageUrl(),
                    contentDescription = it.name,
                    contentScale = ContentScale.FillWidth,
                    error = painterResource(id = R.drawable.pokemon_not_found_image),
                    modifier = Modifier.width(28.dp),
                )
            }
        },
        evolveByHappiness = {
            EvolutionRuleText(text = "Happy: $it")
        },
        evolveByTimeOfDay = {
            Icon(
                painter = painterResource(id = if (it == "day") R.drawable.ic_day else R.drawable.ic_night),
                contentDescription = "Evolve by $it"
            )
        },
        evolveByTrade = {
            Icon(
                painter = painterResource(id = R.drawable.ic_trade),
                contentDescription = "Evolve by trade",
                modifier = Modifier.size(24.dp)
            )
        },
        evolveByKnownMove = {
            EvolutionRuleText(text = "Knows $it move")
        },
        evolveByKnownMoveType = {
            Column {
                EvolutionRuleText(text = buildAnnotatedString {
                    append("Knows ")
                    append(
                        AnnotatedString(
                            it.name,
                            SpanStyle(
                                color = Color.White,
                                background = it.getTypeColor(),
                                fontStyle = FontStyle.Italic
                            )
                        )
                    )
                    append(" type move.")
                })
            }
        }
    )
}

@Composable
private fun EvolutionRuleText(text: String, specifyWidth: Boolean = false) {
    Text(
        text = text,
        fontSize = TextSize.xsmall,
        color = GrayA50,
        softWrap = true,
        modifier = if (specifyWidth) Modifier else Modifier.width(110.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun EvolutionRuleText(text: AnnotatedString) {
    Text(
        text = text,
        fontSize = TextSize.xsmall,
        color = GrayA50,
        softWrap = true,
        modifier = Modifier.width(110.dp),
        textAlign = TextAlign.Center
    )
}