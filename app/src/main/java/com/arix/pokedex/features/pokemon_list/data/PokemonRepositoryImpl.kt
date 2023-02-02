package com.arix.pokedex.features.pokemon_list.data

import com.arix.pokedex.features.pokemon_list.data.data_sources.PokemonLocalDataSource
import com.arix.pokedex.features.pokemon_list.data.data_sources.PokemonRemoteDataSource
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonList
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int): ApiResponse<PokemonList> {
        return remoteDataSource.getPokemonList(offset, limit)
    }

    override suspend fun getPokemon(name: String): ApiResponse<RawPokemonDetails> {
        return remoteDataSource.getPokemon(name)
    }

    override suspend fun getPokemonNames(): Flow<List<String>> {
        return localDataSource.getPokemonNames()
    }

    override suspend fun savePokemonNames(names: List<String>) {
        localDataSource.savePokemonNames(names)
    }

}