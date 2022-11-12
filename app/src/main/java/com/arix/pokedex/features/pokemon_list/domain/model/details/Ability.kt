package com.arix.pokedex.features.pokemon_list.domain.model.details

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)