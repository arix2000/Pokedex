package com.arix.pokedex.features.items.data

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.items.data.data_sources.ItemRemoteDataSource
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.domain.model.item_details.raw.RawItemDetails
import com.arix.pokedex.utils.ApiResponse

class ItemRepositoryImpl(
    private val remoteDataSource: ItemRemoteDataSource,
): ItemRepository {
    override suspend fun getItems(limit: Int, offset: Int, searchQuery:String): ApiResponse<Page<Item>> {
        return remoteDataSource.getItems(limit, offset, searchQuery)
    }

    override suspend fun getItem(itemId: String): ApiResponse<RawItemDetails> {
        return remoteDataSource.getItem(itemId)
    }
}