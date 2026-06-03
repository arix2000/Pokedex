package com.arix.pokedex.core.network

import com.arix.pokedex.features.items.domain.model.item_details.raw.RawItemDetails
import com.arix.pokedex.features.locations.domain.model.details.location_area.RawLocationAreaResponse
import com.arix.pokedex.features.locations.domain.model.details.location_details.RawLocationDetails
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_list.domain.model.RawAbilityDetails
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.features.type_effectiveness.domain.model.RawTypeDetails
import com.arix.pokedex.features.type_effectiveness.domain.model.TypesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("type")
    suspend fun getTypes(
        @Query("limit") limit: Int = 10000,
        @Query("offset") offset: Int = 0
    ): TypesResponse

    @GET("type/{id}")
    suspend fun getType(
        @Path("id") id: Int
    ): RawTypeDetails

    @GET("ability/{id}")
    suspend fun getAbility(
        @Path("id") id: Int
    ): RawAbilityDetails
}