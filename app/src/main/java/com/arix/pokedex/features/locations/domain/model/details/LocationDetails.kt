package com.arix.pokedex.features.locations.domain.model.details

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.features.locations.domain.model.details.location_area.RawLocationAreaResponse
import com.arix.pokedex.features.locations.domain.model.details.location_details.RawLocationDetails
import com.arix.pokedex.theme.RegionColors

data class LocationDetails(
    val name: String,
    val regionName: String,
    val areas: List<LocationArea>
) {

    fun getRegionColor(): Color {
        return when (regionName.lowercase()) {
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

    companion object {
        fun from(
            details: RawLocationDetails,
            areaResponses: Map<String, RawLocationAreaResponse>
        ): LocationDetails {
            val mappedAreas = details.areas.map { areaRef ->
                val fullAreaData = areaResponses[areaRef.name]
                val pokemonList = fullAreaData?.pokemonEncounters
                    ?.map { it.pokemon.name }
                    ?.distinct()
                    ?: emptyList()

                LocationArea(
                    name = areaRef.name,
                    pokemonNames = pokemonList
                )
            }

            return LocationDetails(
                name = details.name,
                regionName = details.region.name,
                areas = mappedAreas
            )
        }
    }
}

