package com.arix.pokedex.features.moves.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.core.Constants.PokemonMovesScreen.MOVES_INITIAL_OFFSET
import com.arix.pokedex.features.moves.domain.model.Move
import com.arix.pokedex.features.moves.domain.use_cases.GetMovesUseCase
import com.arix.pokedex.features.moves.presentation.ui.MovesScreenEvent
import com.arix.pokedex.features.moves.presentation.ui.MovesScreenState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovesViewModel(val getMovesUseCase: GetMovesUseCase) : ViewModel() {

    private val _state = mutableStateOf(MovesScreenState())
    val state: State<MovesScreenState> = _state

    private var getMovesJob: Job? = null

    init {
        getNextMoves()
    }

    fun invokeEvent(event: MovesScreenEvent) {
        when(event) {
            MovesScreenEvent.GetNextMoves -> getNextMoves()
        }
    }

    private fun getNextMoves() {
        getMovesJob = viewModelScope.launch {
            _state.run {
                value = value.copy(isInitialLoading = true)
                with(getMovesUseCase(value.moves?.size ?: MOVES_INITIAL_OFFSET)) {
                    value = when (this) {
                        is Resource.Success -> value.copy(moves = value.moves?.plus(data!!.moves) ?: emptyList<Move>().plus(data!!.moves))
                        is Resource.Error -> value.copy(errorMessage = message)
                    }
                value = value.copy(isInitialLoading = false, isListEndReached = data?.next == null)
                }
            }
        }
    }

}