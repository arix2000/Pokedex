package com.arix.pokedex.features.items.data

import com.arix.pokedex.features.items.domain.model.ItemList
import com.arix.pokedex.features.items.domain.model.move_details.raw.RawItemDetails
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun getItems(limit: Int, offset: Int): ApiResponse<ItemList>

    suspend fun getItem(itemId:String): ApiResponse<RawItemDetails>

    suspend fun saveItemNames(names: List<String>)

    suspend fun getItemNames(): Flow<List<String>>
}