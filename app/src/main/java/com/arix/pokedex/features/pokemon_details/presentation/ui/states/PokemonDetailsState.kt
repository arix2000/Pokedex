package com.arix.pokedex.features.pokemon_details.presentation.ui.states

import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies

data class PokemonDetailsState(
    val pokemonDetails: PokemonDetails? = null,
    val species: PokemonSpecies? = null,
    val evolutionChain: PokemonEvolutionChain? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)