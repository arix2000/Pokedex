package com.arix.pokedex.features.move_details.presentation.ui.components.learnedByPokemon

import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails

data class LearnedByPokemonState(
    val pokemonList: List<PokemonDetails>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)