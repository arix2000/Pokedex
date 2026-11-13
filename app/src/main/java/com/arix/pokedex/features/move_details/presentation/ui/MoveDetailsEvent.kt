package com.arix.pokedex.features.move_details.presentation.ui

sealed class MoveDetailsEvent {
    class LoadMoveDetailsEvent(val moveId: Int) : MoveDetailsEvent()
    class LoadLearnedBySection(val pokemonList: List<String>) : MoveDetailsEvent()
}
