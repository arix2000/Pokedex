package com.arix.pokedex.features.pokemon_list.domain.model.details

import androidx.compose.ui.graphics.Color

data class Type(
    val slot: Int,
    val type: TypeX
) {
    fun getTypeColor(alpha: Float = 1f): Color {
        return type.getTypeColor(alpha)
    }
}