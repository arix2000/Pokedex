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

class GetMovesUseCase(private val repository: MovesRepository) {

    suspend operator fun invoke(offset: Int): Resource<MoveList> {
        with(repository.getMoves(offset, MOVES_ITEM_LIMIT)) {
            return when (this) {
                is Resource.Success -> getMovesFrom(data!!)
                is Resource.Error -> onError(message)
            }
        }
    }

    private suspend fun getMovesFrom(raw: MoveListRaw): Resource<MoveList> {
        val moveResponses = raw.moveLinks.map { it.url.getIdFromUrl() }
            .map { CoroutineScope(Dispatchers.IO).async { getMove(it) } }
        return Resource.Success(MoveList.from(raw, moveResponses.awaitAll()))
    }

    private suspend fun getMove(moveId: Int): Move {
        with(repository.getMove(moveId)) {
            return when (this) {
                is Resource.Success -> data!!
                is Resource.Error -> Move.EMPTY
            }
        }
    }

    private fun <T> onError(message: String?): Resource<T> {
        return Resource.Error(message ?: "Unexpected error")
    }
}







