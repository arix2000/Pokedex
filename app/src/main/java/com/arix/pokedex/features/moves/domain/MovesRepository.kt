package com.arix.pokedex.features.moves.domain

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.utils.ApiResponse

interface MovesRepository {

    suspend fun getMoves(offset: Int, limit: Int, searchQuery: String): ApiResponse<Page<MoveItem>>

    suspend fun getMoves(
        offset: Int,
        limit: Int,
        searchQuery: String,
        limitedList: List<String>
    ): ApiResponse<Page<MoveItem>>

    suspend fun getMove(moveId: String): ApiResponse<RawMove>
}