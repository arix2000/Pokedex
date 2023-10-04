package com.arix.pokedex.features.items.data.data_sources

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.PokeApiService
import com.arix.pokedex.core.network.PokeListsApiService
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.domain.model.item_details.raw.RawItemDetails
import com.arix.pokedex.utils.ApiResponse

class ItemRemoteDataSource(
    private val pokeApiService: PokeApiService,
    private val pokeListsApiService: PokeListsApiService
) : RemoteDataSource() {

    suspend fun getItems(
        limit: Int,
        offset: Int,
        searchQuery: String
    ): ApiResponse<Page<Item>> {
        return if (searchQuery.isBlank())
            makeHttpRequest { pokeListsApiService.getItemList(limit, offset) }
        else
            makeHttpRequest { pokeListsApiService.getItemList(limit, offset, searchQuery) }
    }

    suspend fun getItem(itemId: String): ApiResponse<RawItemDetails> {
        return makeHttpRequest { pokeApiService.getItem(itemId) }
    }
}