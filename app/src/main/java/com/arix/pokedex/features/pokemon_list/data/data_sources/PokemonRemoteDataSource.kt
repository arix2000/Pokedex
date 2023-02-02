package com.arix.pokedex.features.pokemon_list.data.data_sources

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.ApiService
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonList
import com.arix.pokedex.utils.ApiResponse

class PokemonRemoteDataSource(private val apiService: ApiService) : RemoteDataSource() {

    suspend fun getPokemonList(offset: Int, limit: Int): ApiResponse<PokemonList> {
        return makeHttpRequest { apiService.getPokemonList(offset = offset, limit = limit) }
    }

    suspend fun getPokemon(name: String): ApiResponse<RawPokemonDetails> {
        return makeHttpRequest { apiService.getPokemon(name) }
    }
}