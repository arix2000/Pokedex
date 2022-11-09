package com.arix.pokedex.features.poke_list.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import java.util.*

enum class PokemonDetailTag(val color: Color) {
    BABY(Color(0xFF4CAF50)),
    MYTHICAL(Color(0xFF9C27B0)),
    LEGENDARY(Color(0xFFFF5722)),
    NONE(Color(0x00000000));

    fun getName() = name.lowercase().replaceFirstChar(Char::titlecase)

    fun notNone() = this != NONE

    companion object {
        fun fromSpecies(species: PokemonSpecies) = when {
            species.is_baby -> BABY
            species.is_mythical -> MYTHICAL
            species.is_legendary -> LEGENDARY
            else -> NONE
        }
    }
}