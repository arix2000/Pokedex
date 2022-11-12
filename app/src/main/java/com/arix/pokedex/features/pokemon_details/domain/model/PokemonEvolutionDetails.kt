package com.arix.pokedex.features.pokemon_details.domain.model

import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail

data class PokemonEvolutionDetails(
    val pokemonDetails: PokemonDetails,
    val evolutionDetails: List<EvolutionDetail>
)