package com.arix.pokedex.features.poke_list.domain.model.details

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)