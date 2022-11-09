package com.arix.pokedex.features.moves.presentation.ui

sealed class MovesScreenEvent {
    object GetNextMoves : MovesScreenEvent()

    class SearchByQuery(val query: String): MovesScreenEvent()

    object GetNextPage: MovesScreenEvent()
}