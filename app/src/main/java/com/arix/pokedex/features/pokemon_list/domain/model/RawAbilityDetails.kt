package com.arix.pokedex.features.pokemon_list.domain.model

import com.arix.pokedex.features.moves.domain.model.move.EffectEntry
import com.google.gson.annotations.SerializedName

data class RawAbilityDetails(
    @SerializedName("effect_entries")
    val effectEntries: List<EffectEntry>
)
