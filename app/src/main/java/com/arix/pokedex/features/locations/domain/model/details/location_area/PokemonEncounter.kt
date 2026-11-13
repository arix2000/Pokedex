package com.arix.pokedex.features.locations.domain.model.details.location_area

import VersionDetail
import com.arix.pokedex.features.locations.domain.model.details.location_details.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonEncounter(
    val pokemon: NamedApiResource,
    @SerializedName("version_details") val versionDetails: List<VersionDetail>
)