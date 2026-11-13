package com.arix.pokedex.features.common.search_view.domain

import com.arix.pokedex.utils.ApiResponse

data class SearchParams<T>(
    val getItemList: suspend (offset: Int, searchQuery: String) -> ApiResponse<Page<T>>
)
