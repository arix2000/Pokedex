package com.arix.pokedex.features.pokemon_list.domain.model.details

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.theme.StatsColors.StatAttack
import com.arix.pokedex.theme.StatsColors.StatDefault
import com.arix.pokedex.theme.StatsColors.StatDefense
import com.arix.pokedex.theme.StatsColors.StatHp
import com.arix.pokedex.theme.StatsColors.StatSpecialDefense
import com.arix.pokedex.theme.StatsColors.StatSpeed
import com.arix.pokedex.theme.StatsColors.StatSpeedAttack

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val name: String
) {
    val color: Color
        get() = when (name.lowercase()) {
            "hp" -> StatHp
            "attack" -> StatAttack
            "defense" -> StatDefense
            "special-attack" -> StatSpeedAttack
            "special-defense" -> StatSpecialDefense
            "speed" -> StatSpeed
            else -> StatDefault
        }
}
