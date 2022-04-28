package com.arix.pokedex.features.pokemon_details.presentation.ui

sealed class PokemonDetailsEvent {

    class GetInitialData(val pokemonName: String) : PokemonDetailsEvent()

}
