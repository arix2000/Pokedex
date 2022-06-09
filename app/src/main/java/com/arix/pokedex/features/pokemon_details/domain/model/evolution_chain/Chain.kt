package com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain

data class Chain(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<EvolvesTo>,
    val is_baby: Boolean,
    val species: Species
)