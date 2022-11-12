package com.arix.pokedex.features.pokemon_details.presentation.ui.states

import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails

data class PokemonVarietiesState(
    val pokemonVarietiesDetails: List<PokemonDetails>? = null,
    val isLoading: Boolean = false
)