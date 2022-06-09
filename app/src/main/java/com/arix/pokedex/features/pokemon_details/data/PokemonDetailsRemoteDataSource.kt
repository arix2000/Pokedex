package com.arix.pokedex.features.pokemon_details.data

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.ApiService
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.EvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.utils.Resource

class PokemonDetailsRemoteDataSource(private val apiService: ApiService): RemoteDataSource() {

    suspend fun getPokemonSpecies(name: String): Resource<PokemonSpecies> {
        return makeHttpRequest { apiService.getPokemonSpecies(name) }
    }

    suspend fun getPokemonEvolutionChain(evolutionChainId: Int): Resource<PokemonEvolutionChain> {
        return makeHttpRequest { apiService.getPokemonEvolutionChain(evolutionChainId) }
    }
}