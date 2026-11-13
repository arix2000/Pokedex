package com.arix.pokedex.features.locations.presentation.ui

import com.arix.pokedex.features.locations.domain.model.details.LocationDetails
import com.arix.pokedex.features.locations.domain.model.details.UiLocationArea

data class LocationState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val locationDetails: LocationDetails? = null,
    val uiLocationAreas: List<UiLocationArea> = emptyList()
)