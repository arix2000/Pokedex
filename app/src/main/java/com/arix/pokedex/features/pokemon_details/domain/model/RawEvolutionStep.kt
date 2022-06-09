package com.arix.pokedex.features.pokemon_details.domain.model

import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail

data class RawEvolutionStep(
    val pokemonNames: List<String>,
    val pokemonEvolutionDetail: List<EvolutionDetail>
)
