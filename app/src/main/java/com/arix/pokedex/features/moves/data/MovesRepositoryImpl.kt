package com.arix.pokedex.features.moves.data

import com.arix.pokedex.features.moves.data.data_sources.MovesLocalDataSource
import com.arix.pokedex.features.moves.data.data_sources.MovesRemoteDataSource
import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.model.MoveListRaw
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

class MovesRepositoryImpl(
    private val remoteDataSource: MovesRemoteDataSource,
    private val localDataSource: MovesLocalDataSource
) : MovesRepository {
    override suspend fun getMoves(offset: Int, limit: Int): Resource<MoveListRaw> {
        return remoteDataSource.getMoveList(limit, offset)
    }

    override suspend fun getMove(moveId: String): Resource<Move> {
        return remoteDataSource.getMove(moveId)
    }

    override suspend fun getMoveNames(): Flow<List<String>> {
        return localDataSource.getMoveNames()
    }

    override suspend fun saveMoveNames(names: List<String>) {
        localDataSource.saveMoveNames(names)
    }
}