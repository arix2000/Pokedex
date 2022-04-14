package com.arix.pokedex.features.poke_list.presentation.pokemon_list

sealed class PokemonListEvent {
    object RefreshList : PokemonListEvent()
    object GetNextPage : PokemonListEvent()
}
