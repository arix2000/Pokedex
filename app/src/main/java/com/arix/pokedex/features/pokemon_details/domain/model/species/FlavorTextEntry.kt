package com.arix.pokedex.features.pokemon_details.domain.model.species

data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language,
    val version: Version
)