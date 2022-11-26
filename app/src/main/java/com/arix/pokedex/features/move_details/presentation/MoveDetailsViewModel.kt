package com.arix.pokedex.features.move_details.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.move_details.domain.GetMoveUseCase
import com.arix.pokedex.features.move_details.domain.model.UiMove
import com.arix.pokedex.features.move_details.presentation.ui.MoveDetailsState
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoveDetailsViewModel(val getMoveUseCase: GetMoveUseCase) : ViewModel() {

    var state = mutableStateOf(MoveDetailsState())
        private set

    var getMoveJob: Job? = null

    fun loadMove(moveId: Int) {
        getMoveJob = viewModelScope.launch {
            state.run {
                value = value.copy(isLoading = true)
                val response = getMoveUseCase(moveId.toString())
                value = when (response) {
                    is Resource.Success -> value.copy(
                        move = UiMove.fromMove(response.data!!),
                        isLoading = false
                    )
                    is Resource.Error -> value.copy(
                        errorMessage = response.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}