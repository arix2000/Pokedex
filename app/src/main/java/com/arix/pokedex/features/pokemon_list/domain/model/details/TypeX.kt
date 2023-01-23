package com.arix.pokedex.features.pokemon_list.domain.model.details

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.theme.*

data class TypeX(
    val name: String,
    val url: String
) {
    fun getTypeColor(alpha: Float = 1f): Color {
        with(TypeColors) {
            return when (name.lowercase()) {
                "normal" -> normal
                "fire" -> fire
                "water" -> water
                "electric" -> electric
                "grass" -> grass
                "ice" -> ice
                "fighting" -> fighting
                "poison" -> poison
                "ground" -> ground
                "flying" -> flying
                "psychic" -> psychic
                "bug" -> bug
                "rock" -> rock
                "ghost" -> ghost
                "dragon" -> dragon
                "dark" -> dark
                "steel" -> steel
                "fairy" -> fairy
                "shadow" -> shadow
                else -> Color.Black
            }.copy(alpha = alpha)
        }
    }
}