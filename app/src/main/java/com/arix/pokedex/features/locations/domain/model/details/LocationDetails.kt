package com.arix.pokedex.features.locations.domain.model.details

import com.arix.pokedex.features.locations.domain.model.details.location_area.RawLocationAreaResponse
import com.arix.pokedex.features.locations.domain.model.details.location_details.RawLocationDetails

data class LocationDetails(
    val name: String,
    val regionName: String,
    val areas: List<LocationArea>
) {
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

