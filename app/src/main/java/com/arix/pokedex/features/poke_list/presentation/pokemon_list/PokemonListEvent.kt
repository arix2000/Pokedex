package com.arix.pokedex.features.poke_list.presentation.pokemon_list

sealed class PokemonListEvent {
    object GetNextPage : PokemonListEvent()

    class SearchByQuery(val query: String) : PokemonListEvent()

    object OnRetryClicked : PokemonListEvent()
}
