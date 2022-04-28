package com.arix.pokedex.features.poke_list.presentation.ui

import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails

data class PokemonListState(
    val pokemonList: List<PokemonDetails>? = null,
    val isInitialLoading: Boolean = false,
    val isLoadingNext: Boolean = false,
    val isSearching: Boolean = false,
    val isSearchResultsEmpty: Boolean = false,
    val isListEndedReached: Boolean = false,
    val errorMessage: String? = null
) {
    fun isErrorOnInitial() = errorMessage != null && pokemonList == null
}