package com.arix.pokedex.features.pokemon_list.data.data_sources

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.PokeApiService
import com.arix.pokedex.core.network.PokeListsApiService
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.utils.ApiResponse

class PokemonRemoteDataSource(
    private val pokeApiService: PokeApiService,
    private val pokeListsApiService: PokeListsApiService
) : RemoteDataSource() {

    suspend fun getPokemonList(
        offset: Int,
        searchQuery: String,
        limit: Int
    ): ApiResponse<Page<PokemonItem>> {
        return if (searchQuery.isBlank())
            makeHttpRequest { pokeListsApiService.getPokemonList(limit, offset) }
        else
            makeHttpRequest { pokeListsApiService.getPokemonList(searchQuery, limit, offset) }
    }

    suspend fun getPokemon(name: String): ApiResponse<RawPokemonDetails> {
        return makeHttpRequest { pokeApiService.getPokemon(name) }
    }
}