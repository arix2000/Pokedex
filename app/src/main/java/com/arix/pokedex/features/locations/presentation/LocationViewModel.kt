package com.arix.pokedex.features.locations.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.locations.domain.model.details.LocationArea
import com.arix.pokedex.features.locations.domain.model.details.UiLocationArea
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.locations.domain.usecases.GetLocationDetailsUseCase
import com.arix.pokedex.features.locations.domain.usecases.GetLocationsUseCase
import com.arix.pokedex.features.locations.presentation.ui.LocationState
import com.arix.pokedex.features.pokemon_list.domain.use_cases.GetPokemonListByNamesUseCase
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LocationViewModel(
    private val getLocationListUseCase: GetLocationsUseCase,
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase,
    private val getPokemonListByNamesUseCase: GetPokemonListByNamesUseCase,
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
                        val uiLocationAreas = getPokemonsByAreas(data!!.areas)
                        _state.value = _state.value.copy(
                            locationDetails = data,
                            isLoading = false,
                            uiLocationAreas = uiLocationAreas
                        )
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

    suspend fun getPokemonsByAreas(areas: List<LocationArea>): List<UiLocationArea> {
        val areasWithFetchedPokemonList = mutableListOf<UiLocationArea>()
        areas.forEach { area ->
            with(getPokemonListByNamesUseCase(area.pokemonNames)) {
                when (this) {
                    is ApiResponse.Success -> {
                        val location = UiLocationArea(area.name, data!!)
                        areasWithFetchedPokemonList.add(location)
                    }

                    is ApiResponse.Error -> {
                        _state.value = _state.value.copy(errorMessage = message, isLoading = false)
                        return emptyList()
                    }
                }
            }
        }

        return areasWithFetchedPokemonList
    }
}