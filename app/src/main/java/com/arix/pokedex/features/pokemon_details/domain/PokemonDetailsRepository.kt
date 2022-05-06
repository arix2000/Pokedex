package com.arix.pokedex.features.pokemon_details.domain

import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.utils.Resource

interface PokemonDetailsRepository {

    suspend fun getPokemonSpecies(name: String): Resource<PokemonSpecies>
}