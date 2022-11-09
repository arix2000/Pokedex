package com.arix.pokedex.features.poke_list.data

import com.arix.pokedex.features.poke_list.data.data_sources.PokemonLocalDataSource
import com.arix.pokedex.features.poke_list.data.data_sources.PokemonRemoteDataSource
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.list.PokemonList
import com.arix.pokedex.features.poke_list.domain.PokemonRepository
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int): Resource<PokemonList> {
        return remoteDataSource.getPokemonList(offset, limit)
    }

    override suspend fun getPokemon(name: String): Resource<PokemonDetails> {
        return remoteDataSource.getPokemon(name)
    }

    override suspend fun getPokemonNames(): Flow<List<String>> {
        return localDataSource.getPokemonNames()
    }

    override suspend fun savePokemonNames(names: List<String>) {
        localDataSource.savePokemonNames(names)
    }

}