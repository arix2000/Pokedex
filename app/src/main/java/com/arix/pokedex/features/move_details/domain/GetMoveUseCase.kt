package com.arix.pokedex.features.move_details.domain

import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.utils.ApiResponse

class GetMoveUseCase(private val repository: MovesRepository) {

    suspend operator fun invoke(moveId: String): ApiResponse<UiMove> {
        return repository.getMove(moveId).mapSuccess { UiMove.fromRaw(it) }
    }
}