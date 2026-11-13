package com.arix.pokedex.features.common.search_view.domain

import com.google.gson.annotations.SerializedName

open class Page<T>(
    val hasNext: Boolean,
    @SerializedName("results")
    val items: List<T>
)