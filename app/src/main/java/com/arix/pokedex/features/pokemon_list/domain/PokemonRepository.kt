package com.arix.pokedex.features.pokemon_list.domain

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int, searchQuery: String, limit: Int): ApiResponse<Page<PokemonItem>>

    suspend fun getPokemon(name: String): ApiResponse<RawPokemonDetails>

    suspend fun getPokemonNames(): Flow<List<String>>
}