package com.arix.pokedex.features.type_effectiveness.domain.model

import com.arix.pokedex.features.pokemon_list.domain.model.details.Type

data class TypesWithMultiplier(
    val damageCategoryMultiplier: DamageMultiplierCategory,
    val typeToMultiplier:Map<Type, Double>
)
