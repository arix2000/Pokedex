package com.arix.pokedex.features.moves.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.moves.domain.use_cases.GetMoveNamesUseCase
import com.arix.pokedex.features.moves.domain.use_cases.GetMovesByNamesUseCase
import com.arix.pokedex.features.moves.presentation.ui.MovesScreenState
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovesViewModel(
    val getMoveNamesUseCase: GetMoveNamesUseCase,
    val getMovesByNamesUseCase: GetMovesByNamesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovesScreenState())
    val state: State<MovesScreenState> = _state

    private var getMoveNamesJob: Job? = null

    init {
        getMoveNames()
    }

    private fun getMoveNames() {
        getMoveNamesJob = viewModelScope.launch {
            getMoveNamesUseCase().collect { _state.value = _state.value.copy(moveNames = it) }
        }
    }

    suspend fun getMoveListFrom(moveNames: List<String>): List<ApiResponse<UiMove>> {
        return getMovesByNamesUseCase(moveNames)
    }
}