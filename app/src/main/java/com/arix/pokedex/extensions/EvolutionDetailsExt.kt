package com.arix.pokedex.extensions

import androidx.compose.runtime.Composable
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.Item

@Composable
fun List<EvolutionDetail>.ScanEvolutionDetails(
    evolveByLevel: @Composable (Int) -> Unit = {},
    evolveByItem: @Composable (Item) -> Unit = {},
    evolveByHappiness: @Composable (Int) -> Unit = {},
    evolveByTimeOfDay: @Composable (String) -> Unit = {},
    evolveByTrade: @Composable () -> Unit = {}
) {
    this.forEach {
        when {
            it.min_level != null -> evolveByLevel(it.min_level)
            it.item != null -> evolveByItem(it.item)
            it.min_happiness != null -> evolveByHappiness(it.min_happiness)
            it.time_of_day != null -> evolveByTimeOfDay(it.time_of_day)
            it.trade_species != null -> evolveByTrade()
        }
    }

}