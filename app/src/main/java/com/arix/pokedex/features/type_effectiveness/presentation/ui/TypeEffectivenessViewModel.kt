package com.arix.pokedex.features.type_effectiveness.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.type_effectiveness.domain.usecases.GetTypesUseCase
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TypeEffectivenessViewModel(private val getTypesUseCase: GetTypesUseCase) : ViewModel() {
    var state = mutableStateOf(TypeEffectivenessState())
        private set

    var getTypesJob: Job? = null

    init {
        getTypes()
    }

    fun invokeEvent(event: TypeEffectivenessEvent) {
        when (event) {
            is TypeEffectivenessEvent.SelectTypeEvent -> selectType(event)
            is TypeEffectivenessEvent.GetTypesEvent -> getTypes()
        }
    }

    private fun selectType(event: TypeEffectivenessEvent.SelectTypeEvent) {
        state.value =
            state.value.copy(typeEffectivenessList = state.value.typeEffectivenessList.map { typeEffectiveness ->
                if (typeEffectiveness.type == event.selectableType) {
                    typeEffectiveness.copy(type = typeEffectiveness.type.copy(isSelected = !typeEffectiveness.type.isSelected))
                } else {
                    typeEffectiveness
                }
            })
    }

    fun getTypes() {
        getTypesJob?.cancel()
        getTypesJob = viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            val response = getTypesUseCase()
            when (response) {
                is ApiResponse.Success -> {
                    state.value = state.value.copy(
                        typeEffectivenessList = response.data!!,
                        isLoading = false,
                        errorMessage = null
                    )
                }

                is ApiResponse.Error -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        errorMessage = response.message
                    )
                }
            }
        }
    }
}