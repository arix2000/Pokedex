package com.arix.pokedex.features.moves.domain.model.move

data class Normal(
    val use_after: List<UseAfter>,
    val use_before: List<UseBefore>
)