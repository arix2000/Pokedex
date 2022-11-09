package com.arix.pokedex.features.pokemon_details.presentation.ui

import com.arix.pokedex.features.pokemon_details.domain.model.RawEvolutionStep
import com.arix.pokedex.features.pokemon_details.domain.model.species.Variety

sealed class PokemonDetailsEvent {

    class GetInitialData(val pokemonName: String) : PokemonDetailsEvent()

    class GetEvolutionPokemonDetailsList(val pokemonRawEvolutionSteps: List<RawEvolutionStep>) :
        PokemonDetailsEvent()

    class GetPokemonVarietiesDetailsList(val varieties: List<Variety>) : PokemonDetailsEvent()

}
