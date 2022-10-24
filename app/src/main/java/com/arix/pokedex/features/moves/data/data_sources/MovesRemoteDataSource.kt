package com.arix.pokedex.features.moves.data.data_sources

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.ApiService
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.model.MoveListRaw
import com.arix.pokedex.utils.Resource

class MovesRemoteDataSource(private val apiService: ApiService) : RemoteDataSource() {

    suspend fun getMoveList(limit: Int, offset: Int): Resource<MoveListRaw> {
        return makeHttpRequest { apiService.getMoves(limit, offset) }
    }

    suspend fun getMove(moveId: String): Resource<Move> {
        return makeHttpRequest { apiService.getMove(moveId) }
    }
}