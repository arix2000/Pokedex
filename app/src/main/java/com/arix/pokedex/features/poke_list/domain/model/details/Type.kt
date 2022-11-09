package com.arix.pokedex.features.poke_list.domain.model.details

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.theme.*

data class Type(
    val slot: Int,
    val type: TypeX
) {
    fun getTypeColor(alpha: Float = 1f): Color {
        return type.getTypeColor(alpha)
    }
}