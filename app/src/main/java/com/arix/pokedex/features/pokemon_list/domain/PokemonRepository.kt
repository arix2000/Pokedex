package com.arix.pokedex.features.pokemon_list.domain

import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonList
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int, limit: Int): ApiResponse<PokemonList>

    suspend fun getPokemon(name: String): ApiResponse<RawPokemonDetails>

    suspend fun getPokemonNames(): Flow<List<String>>

    suspend fun savePokemonNames(names: List<String>)
}