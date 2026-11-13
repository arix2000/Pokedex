package com.arix.pokedex.features.type_effectiveness.domain.model

import com.arix.pokedex.features.locations.domain.model.details.location_details.NamedApiResource

data class TypesResponse(
    val results: List<NamedApiResource>
)