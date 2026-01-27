package com.arix.pokedex.features.locations.data

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.utils.ApiResponse

class LocationRepository(private val remoteDataSource: LocationRemoteDataSource) {
    suspend fun getLocations(
        limit: Int,
        offset: Int,
        searchQuery: String
    ): ApiResponse<Page<LocationItem>> {
        return remoteDataSource.getLocations(limit, offset, searchQuery)
    }
}