package com.arix.pokedex.features.moves.domain.model

import com.arix.pokedex.features.moves.domain.model.move.*
import com.arix.pokedex.features.moves.domain.model.move.Target
import com.arix.pokedex.features.pokemon_list.domain.model.details.TypeX

data class Move(
    val accuracy: Int?,
    val damage_class: DamageClass,
    val effect_chance: Any,
    val effect_changes: List<Any>,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntry>,
    val generation: Generation,
    val id: Int,
    val learned_by_pokemon: List<LearnedByPokemon>,
    val meta: Meta,
    val name: String,
    val names: List<Name>,
    val power: Int,
    val pp: Int,
    val priority: Int,
    val stat_changes: List<Any>,
    val target: Target,
    val type: TypeX
) {
    companion object {
        val EMPTY = Move(
            0, DamageClass("", ""), 0, emptyList(),
            emptyList(), emptyList(), Generation("", ""), 0, emptyList(),
            Meta(
                Ailment("", ""), 0, Category("", ""),
                0, 0, 0, 0, 0, 0, 0, 0, 0
            ), "", emptyList(), 0, 0, 0, emptyList(), Target("", ""),
            TypeX("", "")
        )
    }
}