package com.arix.pokedex.features.pokemon_list.domain.model.details.raw

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("official-artwork")
    val official_artwork: OfficialArtwork
)