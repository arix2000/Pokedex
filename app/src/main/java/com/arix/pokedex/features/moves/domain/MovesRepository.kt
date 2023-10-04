package com.arix.pokedex.features.moves.domain

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.moves.domain.model.MoveList
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface MovesRepository {

    suspend fun getMoves(offset: Int, limit: Int, searchQuery: String): ApiResponse<Page<MoveItem>>

    suspend fun getMove(moveId: String): ApiResponse<RawMove>

    suspend fun getMoveNames(): Flow<List<String>>

    suspend fun saveMoveNames(names: List<String>)
}