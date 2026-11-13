package com.arix.pokedex.features.move_details.presentation.ui.components.learnedByPokemon

import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem

data class LearnedByPokemonState(
    val pokemonList: List<PokemonItem>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)