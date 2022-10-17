package com.arix.pokedex.features.moves.domain.use_cases

import com.arix.pokedex.features.moves.domain.MovesRepository

class SaveMoveNamesUseCase(private val repository: MovesRepository) {

    suspend operator fun invoke(names: List<String>) {
        return repository.saveMoveNames(names)
    }
}







