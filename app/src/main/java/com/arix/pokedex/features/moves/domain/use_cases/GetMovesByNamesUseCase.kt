package com.arix.pokedex.features.moves.domain.use_cases

import com.arix.pokedex.core.Constants.PokemonMovesScreen.MOVES_ITEM_LIMIT
import com.arix.pokedex.extensions.getIdFromUrl
import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.model.MoveList
import com.arix.pokedex.features.moves.domain.model.MoveListRaw
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class GetMovesByNamesUseCase(private val repository: MovesRepository) {

    suspend operator fun invoke(names: List<String>): List<Move> {
        val moveResponses = names
            .map { CoroutineScope(Dispatchers.IO).async { getMove(it) } }
        return moveResponses.awaitAll()
    }

    private suspend fun getMove(moveId: String): Move {
        with(repository.getMove(moveId)) {
            return when (this) {
                is Resource.Success -> data!!
                is Resource.Error -> Move.EMPTY
            }
        }
    }
}







