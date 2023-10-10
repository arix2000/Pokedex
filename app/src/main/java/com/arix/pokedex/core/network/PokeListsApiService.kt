package com.arix.pokedex.core.network

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeListsApiService {

    @GET("pokemonList")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Page<PokemonItem>

    @POST("pokemonList")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Body pokemonNames: List<String>
    ): Page<PokemonItem>

    @GET("pokemonList/{searchQuery}")
    suspend fun getPokemonList(
        @Path("searchQuery") searchQuery: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Page<PokemonItem>

    @POST("pokemonList/{searchQuery}")
    suspend fun getPokemonList(
        @Path("searchQuery") searchQuery: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Body pokemonNames: List<String>
    ): Page<PokemonItem>

    @GET("moveList")
    suspend fun getMoveList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Page<MoveItem>

    @GET("moveList/{searchQuery}")
    suspend fun getMoveList(
        @Path("searchQuery") searchQuery: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Page<MoveItem>

    @GET("itemList")
    suspend fun getItemList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Page<Item>

    @GET("itemList/{searchQuery}")
    suspend fun getItemList(
        @Path("searchQuery") searchQuery: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Page<Item>
}