package com.arix.pokedex.features.poke_list.domain.model.list

import com.google.gson.annotations.SerializedName

data class PokemonList(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val pokemonList: List<PokemonBasicData>
)