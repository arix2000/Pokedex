package com.arix.pokedex.features.poke_list.data.data_sources

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.ApiService
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.list.PokemonList
import com.arix.pokedex.utils.Resource

class PokemonRemoteDataSource(private val apiService: ApiService) : RemoteDataSource() {

    suspend fun getPokemonList(offset: Int, limit: Int): Resource<PokemonList> {
        return makeHttpRequest { apiService.getPokemonList(offset = offset, limit = limit) }
    }

    suspend fun getPokemon(name: String): Resource<PokemonDetails> {
        return makeHttpRequest { apiService.getPokemon(name) }
    }
}