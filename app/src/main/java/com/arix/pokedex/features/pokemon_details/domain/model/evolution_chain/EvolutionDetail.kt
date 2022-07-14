package com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain

import androidx.compose.runtime.Composable

data class EvolutionDetail(
    val gender: Any?,
    val held_item: Item?,
    val item: Item?,
    val known_move: Any?,
    val known_move_type: Any?,
    val location: Any?,
    val min_affection: Any?,
    val min_beauty: Any?,
    val min_happiness: Int?,
    val min_level: Int?,
    val needs_overworld_rain: Boolean?,
    val party_species: Any?,
    val party_type: Any?,
    val relative_physical_stats: Any?,
    val time_of_day: String?,
    val trade_species: Any?,
    val trigger: Trigger,
    val turn_upside_down: Boolean?
) {
    @Composable
    fun ScanEvolutionDetail(
        evolveByLevel: @Composable (Int) -> Unit = {},
        evolveByItem: @Composable (Item) -> Unit = {},
        evolveByHappiness: @Composable (Int) -> Unit = {},
        evolveByTimeOfDay: @Composable (String) -> Unit = {},
        evolveByTrade: @Composable () -> Unit = {}
    ) {
        when {
            min_level != null -> evolveByLevel(min_level)
            item != null -> evolveByItem(item)
            min_happiness != null -> evolveByHappiness(min_happiness)
            time_of_day != null -> evolveByTimeOfDay(time_of_day)
            trade_species != null -> evolveByTrade()
        }
    }

    fun copy(other: EvolutionDetail): EvolutionDetail {
        with(other) {
            return this.copy(
                gender = gender,
                held_item = held_item,
                item = item,
                known_move = known_move,
                known_move_type = known_move_type,
                location = location,
                min_affection = min_affection,
                min_beauty = min_beauty,
                min_happiness = min_happiness,
                min_level = min_level,
                needs_overworld_rain = needs_overworld_rain,
                party_species = party_species,
                party_type = party_type,
                relative_physical_stats = relative_physical_stats,
                time_of_day = time_of_day,
                trade_species = trade_species,
                trigger = trigger,
                turn_upside_down =turn_upside_down
            )
        }
    }
}