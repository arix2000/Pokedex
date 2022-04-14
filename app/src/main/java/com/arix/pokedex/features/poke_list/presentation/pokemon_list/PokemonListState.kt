package com.arix.pokedex.features.poke_list.presentation.pokemon_list

import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails

data class PokemonListState(
    val pokemonList: MutableList<PokemonDetails>? = null,
    val isInitialLoading: Boolean = false,
    val isLoadingNext: Boolean = false,
    val errorMessage: String? = null //TODO react on error in the UI
)