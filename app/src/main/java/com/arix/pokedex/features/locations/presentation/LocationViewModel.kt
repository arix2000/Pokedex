package com.arix.pokedex.features.locations.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.locations.domain.usecases.GetLocationDetailsUseCase
import com.arix.pokedex.features.locations.domain.usecases.GetLocationsUseCase
import com.arix.pokedex.features.locations.presentation.ui.LocationState
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LocationViewModel(
    private val getLocationListUseCase: GetLocationsUseCase,
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(LocationState())
    val state: State<LocationState> = _state
    private var getLocationDetailsJob: Job? = null


    fun fetchLocationDetails(locationId: Int) {
        getLocationDetailsJob?.cancel()
        getLocationDetailsJob = viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            with(getLocationDetailsUseCase(locationId)) {
                when (this) {
                    is ApiResponse.Success -> {
                        _state.value = _state.value.copy(locationDetails = data)
                    }

                    is ApiResponse.Error -> {
                        _state.value = _state.value.copy(errorMessage = message, isLoading = false)
                    }
                }
            }
        }


    }


    suspend fun getLocations(offset: Int, searchQuery: String): ApiResponse<Page<LocationItem>> {
        return getLocationListUseCase(offset, searchQuery = searchQuery)
    }
}