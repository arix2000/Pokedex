package com.arix.pokedex.features.common.search_view.domain

import com.arix.pokedex.utils.ApiResponse

data class SearchParams<T>(
    val itemNames: List<String>,
    val itemsLimit: Int,
    val emptyItem: T,
    val objectsFromNames: suspend (List<String>) -> List<ApiResponse<T>>
)
