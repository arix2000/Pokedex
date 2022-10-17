package com.arix.pokedex.features.moves.domain.model.move

data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language,
    val version_group: VersionGroup
)