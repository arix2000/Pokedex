package com.arix.pokedex.features.locations.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.locations.domain.usecases.GetLocationsUseCase
import com.arix.pokedex.features.locations.presentation.ui.LocationState
import com.arix.pokedex.utils.ApiResponse

class LocationViewModel(
    private val getLocationListUseCase: GetLocationsUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(LocationState())
    val state: State<LocationState> = _state

    suspend fun getLocations(offset: Int, searchQuery: String): ApiResponse<Page<LocationItem>> {
        return getLocationListUseCase(offset, searchQuery = searchQuery)
    }
}