package com.arix.pokedex.features.moves.data

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.moves.data.data_sources.MovesRemoteDataSource
import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.utils.ApiResponse

class MovesRepositoryImpl(
    private val remoteDataSource: MovesRemoteDataSource,
) : MovesRepository {
    override suspend fun getMoves(
        offset: Int,
        limit: Int,
        searchQuery: String,
        limitedList: List<String>?
    ): ApiResponse<Page<MoveItem>> {
        return if (limitedList != null)
            remoteDataSource.getMoveList(limit, offset, searchQuery, limitedList)
        else
            remoteDataSource.getMoveList(limit, offset, searchQuery)
    }

    override suspend fun getMove(moveId: String): ApiResponse<RawMove> {
        return remoteDataSource.getMove(moveId)
    }
}