package com.arix.pokedex.features.splash_activity

import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.MoveListRaw
import com.arix.pokedex.utils.Resource

class GetRawMoveList(private val repository: MovesRepository) {

    suspend operator fun invoke(offset: Int, limit: Int): Resource<MoveListRaw> {
        return repository.getMoves(offset, limit)
    }
}