package com.arix.pokedex.features.type_effectiveness.presentation.ui

import com.arix.pokedex.features.type_effectiveness.domain.model.TypeEffectiveness

data class TypeEffectivenessState(
    val typeEffectiveness: List<TypeEffectiveness> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)