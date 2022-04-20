package com.arix.pokedex.features.poke_list.domain.model.details

import com.google.gson.annotations.SerializedName

data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork")
    val official_artwork: OfficialArtwork
)