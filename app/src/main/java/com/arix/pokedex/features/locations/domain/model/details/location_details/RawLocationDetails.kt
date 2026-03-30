package com.arix.pokedex.features.locations.domain.model.details.location_details

data class RawLocationDetails(
    val id: Int,
    val name: String,
    val region: NamedApiResource,
    val areas: List<NamedApiResource>,
    val names: List<LocationName>
)