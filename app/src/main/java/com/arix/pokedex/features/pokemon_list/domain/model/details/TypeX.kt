package com.arix.pokedex.features.pokemon_list.domain.model.details

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.theme.*

data class TypeX(
    val name: String,
    val url: String
) {
    fun getTypeColor(alpha: Float = 1f): Color {
        return when(name.lowercase()) {
            "normal" -> TypeNormal
            "fire" -> TypeFire
            "water" -> TypeWater
            "electric" -> TypeElectric
            "grass" -> TypeGrass
            "ice" -> TypeIce
            "fighting" -> TypeFighting
            "poison" -> TypePoison
            "ground" -> TypeGround
            "flying" -> TypeFlying
            "psychic" -> TypePsychic
            "bug" -> TypeBug
            "rock" -> TypeRock
            "ghost" -> TypeGhost
            "dragon" -> TypeDragon
            "dark" -> TypeDark
            "steel" -> TypeSteel
            "fairy" -> TypeFairy
            else -> Color.Black
        }.copy(alpha = alpha)
    }
}