package com.arix.pokedex.core.network

import com.arix.pokedex.features.items.domain.model.item_details.raw.RawItemDetails
import com.arix.pokedex.features.locations.domain.model.details.location_area.RawLocationAreaResponse
import com.arix.pokedex.features.locations.domain.model.details.location_details.RawLocationDetails
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): RawPokemonDetails

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(
        @Path("name") name: String
    ): PokemonSpecies

    @GET("evolution-chain/{evolutionChainId}")
    suspend fun getPokemonEvolutionChain(
        @Path("evolutionChainId") evolutionChainId: Int
    ): PokemonEvolutionChain

    @GET("move/{moveId}")
    suspend fun getMove(
        @Path("moveId") moveId: String
    ): RawMove

    @GET("item/{itemId}")
    suspend fun getItem(
        @Path("itemId") itemId: String
    ): RawItemDetails

    @GET("location/{locationId}")
    suspend fun getLocation(
        @Path("locationId") locationId: String
    ): RawLocationDetails

    @GET("location-area/{locationAreaId}")
    suspend fun getLocationArea(
        @Path("locationAreaId") locationAreaId: String
    ): RawLocationAreaResponse


}