package com.arix.pokedex.features.move_details.presentation.ui

import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonBasicData

sealed class MoveDetailsEvent {
    class LoadMoveDetailsEvent(val moveId: Int) : MoveDetailsEvent()
    class LoadLearnedBySection(val pokemonList: List<PokemonBasicData>) : MoveDetailsEvent()
}
