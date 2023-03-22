package com.arix.pokedex.features.items.domain.model.move_details.raw

data class EffectEntry(
    val effect: String,
    val language: Language,
    val short_effect: String
)