package com.arix.pokedex.features.type_effectiveness.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.type_effectiveness.domain.model.DamageMultiplierCategory
import com.arix.pokedex.features.type_effectiveness.domain.model.TypeEffectiveness
import com.arix.pokedex.features.type_effectiveness.domain.model.TypesWithMultiplier
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
            is TypeEffectivenessEvent.ClearTypesEvent -> clearTypes()
        }
    }

    private fun clearTypes() {
        val typeEffectivenessListWithNewSelected =
            state.value.typeEffectivenessList.map { typeEffectiveness ->
                    typeEffectiveness.copy(type = typeEffectiveness.type.copy(isSelected = false))
            }
        state.value =
            state.value.copy(
                typeEffectivenessList = typeEffectivenessListWithNewSelected,
                mergedTypeEffectiveness = emptyList()
            )
    }

    private fun selectType(event: TypeEffectivenessEvent.SelectTypeEvent) {
        val typeEffectivenessListWithNewSelected =
            state.value.typeEffectivenessList.map { typeEffectiveness ->
                if (typeEffectiveness.type == event.selectableType) {
                    typeEffectiveness.copy(type = typeEffectiveness.type.copy(isSelected = !typeEffectiveness.type.isSelected))
                } else {
                    typeEffectiveness
                }
            }
        val mergedTypeEffectiveness =
            mergeTypeEffectivenessIfNeeded(typeEffectivenessListWithNewSelected.filter { it.type.isSelected })

        state.value =
            state.value.copy(
                typeEffectivenessList = typeEffectivenessListWithNewSelected,
                mergedTypeEffectiveness = mergedTypeEffectiveness
            )
    }

    private fun mergeTypeEffectivenessIfNeeded(typeEffectivenessList: List<TypeEffectiveness>): List<TypesWithMultiplier> {
        val typesToMultipliersMap = mutableMapOf<Type, Double>()
        typeEffectivenessList.forEach { typeEffectiveness ->
            val newTypesToMultipliers: Map<Type, Double> =
                typeEffectiveness.typesToMultipliers.mapValues { it.value.baseMultiplier }
            if (typesToMultipliersMap.isEmpty()) {
                typesToMultipliersMap.putAll(newTypesToMultipliers)
            } else {
                for ((key, entry) in typesToMultipliersMap) {
                    typesToMultipliersMap[key] = entry * newTypesToMultipliers[key]!!
                }
            }
        }
        val typesWithMultiplierList = mutableListOf<TypesWithMultiplier>()
        DamageMultiplierCategory.entries.forEach { damageMultiplierCategory ->
            typesWithMultiplierList.add(
                TypesWithMultiplier(
                    damageMultiplierCategory,
                    typesToMultipliersMap.filter { multiplier ->
                        damageMultiplierCategory.multiplierRange.contains(
                            multiplier.value
                        )
                    })
            )
        }
        return typesWithMultiplierList
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