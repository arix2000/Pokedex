package com.arix.pokedex.features.pokemon_details.data

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.PokeApiService
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.utils.ApiResponse

class PokemonDetailsRemoteDataSource(private val pokeApiService: PokeApiService): RemoteDataSource() {

    suspend fun getPokemonSpecies(name: String): ApiResponse<PokemonSpecies> {
        return makeHttpRequest { pokeApiService.getPokemonSpecies(name) }
    }

    suspend fun getPokemonEvolutionChain(evolutionChainId: Int): ApiResponse<PokemonEvolutionChain> {
        return makeHttpRequest { pokeApiService.getPokemonEvolutionChain(evolutionChainId) }
    }
}