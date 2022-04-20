package com.arix.pokedex.features.poke_list.domain.model.details

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.theme.*

data class Type(
    val slot: Int,
    val type: TypeX
) {
    fun getTypeColor(): Color {
        return when(type.name.lowercase()) {
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
        }
    }
}