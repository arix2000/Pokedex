package com.arix.pokedex.features.locations.domain.usecases

import com.arix.pokedex.core.Constants.ItemsScreenConst.ITEMS_LIMIT
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.locations.data.LocationRepository
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.utils.ApiResponse

class GetLocationsUseCase(private val repository: LocationRepository) {
    suspend operator fun invoke(
        offset: Int,
        limit: Int = ITEMS_LIMIT,
        searchQuery: String,
        limitedList: List<String>? = null
    ): ApiResponse<Page<LocationItem>> {
        return repository.getLocations(limit, offset, searchQuery)
    }
}