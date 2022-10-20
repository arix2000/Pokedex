package com.arix.pokedex.features.moves.presentation.ui

import com.arix.pokedex.features.moves.domain.model.Move

data class MovesScreenState(
    val moves: List<Move>? = null,
    val isInitialLoading: Boolean = false,
    val errorMessage: String? = null,
    val isListEndReached: Boolean = false,
    val searching: Boolean = false,
    val emptySearchResult: Boolean = false
) {
    fun isErrorOnInitial() = errorMessage != null && moves == null
}