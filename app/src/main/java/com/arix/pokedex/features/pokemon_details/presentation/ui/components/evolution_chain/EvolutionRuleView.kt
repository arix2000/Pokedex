package com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain

import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail
import com.arix.pokedex.theme.GrayA50
import com.arix.pokedex.theme.TextSize

@Composable
fun EvolutionRuleView(evolutionDetails: EvolutionDetail) {
    evolutionDetails.ScanEvolutionDetail(
        evolveByLevel = {
            Text(
                text = "Level $it",
                fontSize = TextSize.xsmall,
                color = GrayA50
            )
        },
        evolveByItem = {
            AsyncImage(
                model = it.getImageUrl(),
                contentDescription = it.name,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.pokemon_not_found_image),
                modifier = Modifier.width(28.dp)
            )
        }
    )
}
