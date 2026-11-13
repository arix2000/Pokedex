package com.arix.pokedex.features.locations.data

import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.locations.domain.model.details.location_area.RawLocationAreaResponse
import com.arix.pokedex.features.locations.domain.model.details.location_details.RawLocationDetails
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.utils.ApiResponse

class LocationRepository(private val remoteDataSource: LocationRemoteDataSource) {
    suspend fun getLocations(
        limit: Int,
        offset: Int,
        searchQuery: String,
        limitedList: List<String>? = null
    ): ApiResponse<Page<LocationItem>> {
        return if (limitedList == null)
            remoteDataSource.getLocations(limit, offset, searchQuery)
        else
            remoteDataSource.getLocations(limit, offset, searchQuery, limitedList)
    }

    suspend fun getLocationDetails(locationId: Int): ApiResponse<RawLocationDetails> {
        return remoteDataSource.getLocationDetails(locationId)
    }

    suspend fun getLocationArea(locationAreaId: Int): ApiResponse<RawLocationAreaResponse> {
        return remoteDataSource.getLocationArea(locationAreaId)
    }
}