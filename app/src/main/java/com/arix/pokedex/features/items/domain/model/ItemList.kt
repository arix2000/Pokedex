package com.arix.pokedex.features.items.domain.model

import com.google.gson.annotations.SerializedName

data class ItemList(
    @SerializedName("results")
    val items: List<ItemLink>
)
