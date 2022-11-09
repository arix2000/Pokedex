package com.arix.pokedex.features.pokemon_details.presentation.ui.states

import com.arix.pokedex.features.pokemon_details.domain.model.EvolutionStep

data class PokemonEvolutionState(
    val pokemonEvolutionSteps: List<EvolutionStep>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
