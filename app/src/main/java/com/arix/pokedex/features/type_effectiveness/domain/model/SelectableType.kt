package com.arix.pokedex.features.type_effectiveness.domain.model

import com.arix.pokedex.features.pokemon_list.domain.model.details.Type

data class SelectableType(
    val isSelected: Boolean = false,
    val type: Type
) {
    fun getTypeColor() = type.getTypeColor()

    val name get() = type.name
}
