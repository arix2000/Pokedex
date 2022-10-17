package com.arix.pokedex.features.moves.domain

import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.model.MoveListRaw
import com.arix.pokedex.utils.Resource

interface MovesRepository {

    suspend fun getMoves(offset: Int, limit: Int): Resource<MoveListRaw>

    suspend fun getMove(moveId: Int): Resource<Move>
}