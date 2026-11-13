package com.arix.pokedex.features.locations.data

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.PokeApiService
import com.arix.pokedex.core.network.PokeListsApiService
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.locations.domain.model.details.location_area.RawLocationAreaResponse
import com.arix.pokedex.features.locations.domain.model.details.location_details.RawLocationDetails
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.utils.ApiResponse

class LocationRemoteDataSource(
    private val pokeApiService: PokeApiService,
    private val pokeListsApiService: PokeListsApiService
) : RemoteDataSource() {

    suspend fun getLocations(
        limit: Int,
        offset: Int,
        searchQuery: String
    ): ApiResponse<Page<LocationItem>> {
        return if (searchQuery.isBlank())
            makeHttpRequest { pokeListsApiService.getLocationList(limit, offset) }
        else
            makeHttpRequest { pokeListsApiService.getLocationList(searchQuery, limit, offset) }
    }

    suspend fun getLocations(
        limit: Int,
        offset: Int,
        searchQuery: String,
        limitedList: List<String>
    ): ApiResponse<Page<LocationItem>> {
        return if (searchQuery.isBlank())
            makeHttpRequest { pokeListsApiService.getLocationList(limit, offset, limitedList) }
        else
            makeHttpRequest {
                pokeListsApiService.getLocationList(
                    searchQuery,
                    limit,
                    offset,
                    limitedList
                )
            }
    }

    suspend fun getLocationDetails(locationId: Int): ApiResponse<RawLocationDetails> {
        return makeHttpRequest { pokeApiService.getLocation(locationId.toString()) }
    }

    suspend fun getLocationArea(locationAreaId: Int): ApiResponse<RawLocationAreaResponse> {
        return makeHttpRequest { pokeApiService.getLocationArea(locationAreaId.toString()) }
    }
}