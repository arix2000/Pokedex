package com.arix.pokedex.features.locations.domain.model.details

import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem

data class UiLocationArea(
    val name: String,
    val pokemonNames: List<PokemonItem>,
)