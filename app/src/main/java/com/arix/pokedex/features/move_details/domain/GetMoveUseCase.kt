package com.arix.pokedex.features.move_details.domain

import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.utils.Resource

class GetMoveUseCase(private val repository: MovesRepository) {

    suspend operator fun invoke(moveId: String): Resource<Move> {
        return repository.getMove(moveId)
    }
}