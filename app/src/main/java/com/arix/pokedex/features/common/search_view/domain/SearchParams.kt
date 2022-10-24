package com.arix.pokedex.features.common.search_view.domain

import com.arix.pokedex.utils.Resource

data class SearchParams<T>(
    val itemNames: List<String>,
    val itemsLimit: Int,
    val emptyItem: T,
    val objectFromNames: suspend (List<String>) -> List<Resource<T>>
)
