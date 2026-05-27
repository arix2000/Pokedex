package com.arix.pokedex.features.type_effectiveness.domain.model

import com.arix.pokedex.features.pokemon_list.domain.model.details.Type

data class TypeEffectiveness(
    val type: SelectableType,
    val multipliersToTypes: Map<DamageMultiplier, List<Type>>
)