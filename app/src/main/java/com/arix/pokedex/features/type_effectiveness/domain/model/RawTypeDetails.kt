package com.arix.pokedex.features.type_effectiveness.domain.model

import com.google.gson.annotations.SerializedName

data class RawTypeDetails(
    val name: String,
    @SerializedName("damage_relations")
    val damageRelations: RawTypeDamageRelations
)