package com.arix.pokedex.features.moves.domain.model.move

data class Meta(
    val ailment: Ailment,
    val ailment_chance: Int,
    val category: Category,
    val crit_rate: Int,
    val drain: Int,
    val flinch_chance: Int,
    val healing: Int,
    val max_hits: Any,
    val max_turns: Any,
    val min_hits: Any,
    val min_turns: Any,
    val stat_chance: Int
)