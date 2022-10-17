package com.arix.pokedex.features.moves.presentation.ui

sealed class MovesScreenEvent {
    object GetNextMoves : MovesScreenEvent()
}