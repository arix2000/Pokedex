package com.arix.pokedex.features.splash_activity

import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.MoveList
import com.arix.pokedex.utils.ApiResponse

class GetRawMoveList(private val repository: MovesRepository) {

    suspend operator fun invoke(offset: Int, limit: Int): ApiResponse<MoveList> {
        return repository.getMoves(offset, limit)
    }
}