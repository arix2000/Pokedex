package com.arix.pokedex.features.items.data

import com.arix.pokedex.features.items.data.data_sources.ItemLocalDataSource
import com.arix.pokedex.features.items.data.data_sources.ItemRemoteDataSource
import com.arix.pokedex.features.items.domain.model.ItemList
import com.arix.pokedex.features.items.domain.model.move_details.raw.RawItemDetails
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val remoteDataSource: ItemRemoteDataSource,
    private val localDataSource: ItemLocalDataSource
): ItemRepository {
    override suspend fun getItems(limit: Int, offset: Int): ApiResponse<ItemList> {
        return remoteDataSource.getItems(limit, offset)
    }

    override suspend fun getItem(itemId: String): ApiResponse<RawItemDetails> {
        return remoteDataSource.getItem(itemId)
    }

    override suspend fun saveItemNames(names: List<String>) {
        return localDataSource.saveItemNames(names)
    }

    override suspend fun getItemNames(): Flow<List<String>> {
        return localDataSource.getItemNames()
    }
}