package com.arix.pokedex.features.pokemon_details.presentation.ui

import com.arix.pokedex.features.pokemon_details.domain.model.RawEvolutionStep

sealed class PokemonDetailsEvent {

    class GetInitialData(val pokemonName: String) : PokemonDetailsEvent()

    class GetEvolutionPokemonDetailsList(val pokemonRawEvolutionSteps: List<RawEvolutionStep>) :
        PokemonDetailsEvent()

}
