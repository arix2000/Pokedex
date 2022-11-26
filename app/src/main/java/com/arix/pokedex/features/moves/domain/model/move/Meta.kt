package com.arix.pokedex.features.moves.domain.model.move

data class Meta(
    val ailment: Ailment,
    val ailment_chance: Int,
    val crit_rate: Int,
    val drain: Int,
    val flinch_chance: Int,
    val healing: Int,
    val max_hits: Int?,
    val max_turns: Int?,
    val min_hits: Int?,
    val min_turns: Int?,
    val stat_chance: Int
) {
    fun turnsNotNull() = min_turns != null && max_turns != null
    fun hitsNotNull() = min_hits != null && max_hits != null

    fun getTurnsRange() = if(min_turns != max_turns) "${min_turns}-${max_turns}" else max_turns
    fun getHitsRange() = if(min_hits != max_hits) "${min_hits}-${max_hits}" else max_hits
}