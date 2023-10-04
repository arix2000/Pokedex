package com.arix.pokedex.features.moves.presentation

import androidx.lifecycle.ViewModel
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.moves.domain.use_cases.GetMoveListUseCase
import com.arix.pokedex.utils.ApiResponse

class MovesViewModel(
    val getMoveListUseCase: GetMoveListUseCase
) : ViewModel() {
    suspend fun getMoveList(offset: Int, searchQuery: String): ApiResponse<Page<MoveItem>> {
        return getMoveListUseCase(offset, searchQuery = searchQuery)
    }
}