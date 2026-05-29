package com.arix.pokedex.features.type_effectiveness.presentation.ui

import com.arix.pokedex.features.type_effectiveness.domain.model.TypeEffectiveness
import com.arix.pokedex.features.type_effectiveness.domain.model.TypesWithMultiplier

data class TypeEffectivenessState(
    val typeEffectivenessList: List<TypeEffectiveness> = emptyList(),
    val mergedTypeEffectiveness: List<TypesWithMultiplier> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    fun getSelectedCount() = typeEffectivenessList.count { it.type.isSelected }
}