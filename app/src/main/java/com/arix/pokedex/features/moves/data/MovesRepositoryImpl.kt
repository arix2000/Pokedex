package com.arix.pokedex.features.moves.data

import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.model.MoveListRaw
import com.arix.pokedex.utils.Resource

class MovesRepositoryImpl(private val remoteDataSource: MovesRemoteDataSource) : MovesRepository {
    override suspend fun getMoves(offset: Int, limit: Int): Resource<MoveListRaw> {
        return remoteDataSource.getMoveList(limit, offset)
    }

    override suspend fun getMove(moveId: Int): Resource<Move> {
        return remoteDataSource.getMove(moveId)
    }
}