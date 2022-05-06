package com.arix.pokedex.features.pokemon_details.data

import com.arix.pokedex.features.pokemon_details.domain.PokemonDetailsRepository
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.utils.Resource

class PokemonDetailsRepositoryImpl(private val remoteDataSource: PokemonDetailsRemoteDataSource) :
    PokemonDetailsRepository {

    override suspend fun getPokemonSpecies(name: String): Resource<PokemonSpecies> {
        return remoteDataSource.getPokemonSpecies(name)
    }
}