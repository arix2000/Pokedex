package com.arix.pokedex.features.poke_list.presentation.ui

sealed class PokemonListEvent {
    object GetNextPage : PokemonListEvent()

    class SearchByQuery(val query: String) : PokemonListEvent()

    object OnRetryClicked : PokemonListEvent()
}
