package com.arix.pokedex.features.pokemon_details.presentation.ui

import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails

data class PokemonDetailsState(
    val pokemonDetails: PokemonDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    fun isError() = errorMessage == null
}