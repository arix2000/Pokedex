package com.arix.pokedex.features.pokemon_list.data

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.pokemon_list.data.data_sources.PokemonRemoteDataSource
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.utils.ApiResponse

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
) : PokemonRepository {

    override suspend fun getPokemonList(
        offset: Int,
        searchQuery: String,
        limit: Int,
        limitedList: List<String>
    ): ApiResponse<Page<PokemonItem>> {
        return remoteDataSource.getPokemonList(offset, searchQuery, limit, limitedList)
    }

    override suspend fun getPokemon(name: String): ApiResponse<RawPokemonDetails> {
        return remoteDataSource.getPokemon(name)
    }
}