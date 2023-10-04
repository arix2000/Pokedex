package com.arix.pokedex.features.items.data

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.domain.model.item_details.raw.RawItemDetails
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun getItems(limit: Int, offset: Int, searchQuery:String): ApiResponse<Page<Item>>

    suspend fun getItem(itemId:String): ApiResponse<RawItemDetails>

    suspend fun saveItemNames(names: List<String>)

    suspend fun getItemNames(): Flow<List<String>>
}