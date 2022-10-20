package com.arix.pokedex.features.moves.domain

import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.model.MoveListRaw
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovesRepository {

    suspend fun getMoves(offset: Int, limit: Int): Resource<MoveListRaw>

    suspend fun getMove(moveId: String): Resource<Move>

    suspend fun getMoveNames(): Flow<List<String>>

    suspend fun saveMoveNames(names: List<String>)
}