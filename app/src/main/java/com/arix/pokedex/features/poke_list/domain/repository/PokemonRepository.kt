package com.arix.pokedex.features.poke_list.domain.repository

import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.list.PokemonList
import com.arix.pokedex.utils.Resource

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int, limit: Int): Resource<PokemonList>

    suspend fun getPokemon(name: String): Resource<PokemonDetails>
}