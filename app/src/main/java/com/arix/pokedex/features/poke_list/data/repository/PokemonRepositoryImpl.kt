package com.arix.pokedex.features.poke_list.data.repository

import com.arix.pokedex.features.poke_list.data.remote_data_source.PokemonRemoteDataSource
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.list.PokemonList
import com.arix.pokedex.features.poke_list.domain.repository.PokemonRepository
import com.arix.pokedex.utils.Resource

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int): Resource<PokemonList> {
        return remoteDataSource.getPokemonList(offset, limit)
    }

    override suspend fun getPokemon(name: String): Resource<PokemonDetails> {
        return remoteDataSource.getPokemon(name)
    }

}