package com.arix.pokedex.features.moves.domain.use_cases

import com.arix.pokedex.core.Constants.MoveScreen.MOVES_ITEM_LIMIT
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.moves.domain.MovesRepository
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.domain.model.MoveList
import com.arix.pokedex.utils.ApiResponse

class GetMoveListUseCase(private val repository: MovesRepository) {

    suspend operator fun invoke(
        offset: Int,
        limit: Int = MOVES_ITEM_LIMIT,
        searchQuery: String
    ): ApiResponse<Page<MoveItem>> {
        return repository.getMoves(offset, limit, searchQuery)
    }
}