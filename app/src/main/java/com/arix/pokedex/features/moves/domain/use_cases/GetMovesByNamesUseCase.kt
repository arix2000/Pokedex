package com.arix.pokedex.features.moves.domain.use_cases

import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class GetMovesByNamesUseCase(private val repository: MovesRepository) {

    suspend operator fun invoke(names: List<String>): List<Resource<Move>> {
        val moveResponses = names
            .map { CoroutineScope(Dispatchers.IO).async { repository.getMove(it) } }
        return moveResponses.awaitAll()
    }
}







