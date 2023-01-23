package com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain

import com.arix.pokedex.features.moves.domain.model.move_list.MoveLink
import com.arix.pokedex.features.pokemon_list.domain.model.details.TypeX

data class EvolutionDetail(
    val gender: Any?,
    val held_item: Item?,
    val item: Item?,
    val known_move: MoveLink?,
    val known_move_type: TypeX?,
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
)