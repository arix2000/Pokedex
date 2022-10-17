package com.arix.pokedex.features.moves.domain.model.move

data class EffectEntry(
    val effect: String,
    val language: Language,
    val short_effect: String
)