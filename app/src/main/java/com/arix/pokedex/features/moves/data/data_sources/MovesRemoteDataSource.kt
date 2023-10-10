package com.arix.pokedex.features.moves.data.data_sources

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.PokeApiService
import com.arix.pokedex.core.network.PokeListsApiService
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.moves.domain.model.MoveList
import com.arix.pokedex.utils.ApiResponse

class MovesRemoteDataSource(
    private val pokeApiService: PokeApiService,
    private val pokeListsApiService: PokeListsApiService
) : RemoteDataSource() {

    suspend fun getMoveList(
        limit: Int,
        offset: Int,
        searchQuery: String
    ): ApiResponse<Page<MoveItem>> {
        return if (searchQuery.isBlank())
            makeHttpRequest { pokeListsApiService.getMoveList(limit, offset) }
        else makeHttpRequest { pokeListsApiService.getMoveList(searchQuery, limit, offset) }
    }

    suspend fun getMove(moveId: String): ApiResponse<RawMove> {
        return makeHttpRequest { pokeApiService.getMove(moveId) }
    }
}