package com.arix.pokedex.core.network

import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.poke_list.domain.model.list.PokemonList
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): PokemonDetails

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(
        @Path("name") name: String
    ): PokemonSpecies

}