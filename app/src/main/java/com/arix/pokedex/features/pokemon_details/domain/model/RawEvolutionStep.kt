package com.arix.pokedex.features.pokemon_details.domain.model

import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail

data class RawEvolutionStep(
    val pokemonToEvolutionDetailsMap: Map<Int, List<EvolutionDetail>>
)
