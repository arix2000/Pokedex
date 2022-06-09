package com.arix.pokedex.features.pokemon_details.presentation.ui.components.evolution_chain

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.extensions.ScanEvolutionDetails
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail
import com.arix.pokedex.theme.GrayA50
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.TextSize

@Composable
fun EvolutionRuleView(evolutionDetails: List<EvolutionDetail>) {
    evolutionDetails.ScanEvolutionDetails(
        evolveByLevel = {
            Text(
                text = "Level $it",
                fontSize = TextSize.xsmall,
                color = GrayA50
            )
        },
        evolveByItem = {
            Text(
                text = it.name,
                fontSize = TextSize.xsmall,
                color = GrayA50
            )
        }
    )
}

@Preview
@Composable
fun EvolutionRuleViewPreview() {
    PokedexTheme {
        Surface {

        }
    }
}
