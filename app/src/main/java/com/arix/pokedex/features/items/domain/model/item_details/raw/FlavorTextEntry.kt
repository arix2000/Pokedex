package com.arix.pokedex.features.items.domain.model.item_details.raw

data class FlavorTextEntry(
    val language: Language,
    val text: String,
    val version_group: VersionGroup
)