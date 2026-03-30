package com.arix.pokedex.features.locations.domain.model.details.location_area

import com.google.gson.annotations.SerializedName

data class RawLocationAreaResponse(
    @SerializedName("pokemon_encounters") val pokemonEncounters: List<PokemonEncounter>
)