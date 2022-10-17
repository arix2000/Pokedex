package com.arix.pokedex.features.moves.data

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.ApiService
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.model.MoveListRaw
import com.arix.pokedex.utils.Resource

class MovesRemoteDataSource(private val apiService: ApiService) : RemoteDataSource() {

    suspend fun getMoveList(limit: Int, offset: Int): Resource<MoveListRaw> {
        return makeHttpRequest { apiService.getMoves(limit, offset) }
    }

    suspend fun getMove(moveId: Int): Resource<Move> {
        return makeHttpRequest { apiService.getMove(moveId) }
    }
}