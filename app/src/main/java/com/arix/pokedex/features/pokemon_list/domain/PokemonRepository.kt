package com.arix.pokedex.features.pokemon_list.domain

import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonList
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int, limit: Int): Resource<PokemonList>

    suspend fun getPokemon(name: String): Resource<PokemonDetails>

    suspend fun getPokemonNames(): Flow<List<String>>

    suspend fun savePokemonNames(names: List<String>)
}