package com.arix.pokedex.features.items.data.data_sources

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.ApiService
import com.arix.pokedex.features.items.domain.model.ItemList
import com.arix.pokedex.utils.ApiResponse

class ItemRemoteDataSource(private val apiService: ApiService) : RemoteDataSource() {

    suspend fun getItems(limit: Int, offset: Int): ApiResponse<ItemList> {
        return makeHttpRequest { apiService.getItems(limit, offset) }
    }
}