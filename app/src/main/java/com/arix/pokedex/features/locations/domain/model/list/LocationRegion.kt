package com.arix.pokedex.features.locations.domain.model.list

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.theme.RegionColors

data class LocationRegion(val name: String, val url: String) {

    fun getRegionColor(): Color {
        return when (name.lowercase()) {
            "kanto" -> RegionColors.kanto
            "johto" -> RegionColors.johto
            "hoenn" -> RegionColors.hoenn
            "sinnoh" -> RegionColors.sinnoh
            "unova" -> RegionColors.unova
            "kalos" -> RegionColors.kalos
            "alola" -> RegionColors.alola
            "galar" -> RegionColors.galar
            "hisui" -> RegionColors.hisui
            "paldea" -> RegionColors.paldea
            else -> Color.Black
        }
    }
}
