package com.arix.pokedex.features.moves.data.data_sources

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.ApiService
import com.arix.pokedex.features.moves.domain.model.RawMove
import com.arix.pokedex.features.moves.domain.model.MoveList
import com.arix.pokedex.utils.ApiResponse

class MovesRemoteDataSource(private val apiService: ApiService) : RemoteDataSource() {

    suspend fun getMoveList(limit: Int, offset: Int): ApiResponse<MoveList> {
        return makeHttpRequest { apiService.getMoves(limit, offset) }
    }

    suspend fun getMove(moveId: String): ApiResponse<RawMove> {
        return makeHttpRequest { apiService.getMove(moveId) }
    }
}