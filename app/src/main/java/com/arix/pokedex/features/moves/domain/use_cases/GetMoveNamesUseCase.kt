package com.arix.pokedex.features.moves.domain.use_cases

import com.arix.pokedex.features.moves.domain.MovesRepository
import kotlinx.coroutines.flow.Flow

class GetMoveNamesUseCase(private val repository: MovesRepository) {

    suspend operator fun invoke(): Flow<List<String>> {
        return repository.getMoveNames()
    }
}







