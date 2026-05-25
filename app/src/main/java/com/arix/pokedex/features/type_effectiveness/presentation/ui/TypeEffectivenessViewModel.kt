package com.arix.pokedex.features.type_effectiveness.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.type_effectiveness.domain.usecases.GetTypesUseCase
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TypeEffectivenessViewModel(private val getTypesUseCase: GetTypesUseCase) : ViewModel() {
    private val _state = mutableStateOf(TypeEffectivenessState())
    val state: TypeEffectivenessState = _state.value

    var getTypesJob: Job? = null

    init {
        getTypes()
    }

    fun getTypes() {
        getTypesJob?.cancel()
        getTypesJob = viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val response = getTypesUseCase()
            when (response) {
                is ApiResponse.Success -> _state.value = _state.value.copy(
                    typeEffectiveness = response.data!!,
                    isLoading = false,
                    errorMessage = null
                )

                is ApiResponse.Error -> _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = response.message
                )
            }
        }
    }
}