package com.arix.pokedex.features.locations.domain.usecases

import com.arix.pokedex.extensions.getIdFromUrl
import com.arix.pokedex.features.locations.data.LocationRepository
import com.arix.pokedex.features.locations.domain.model.details.LocationDetails
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GetLocationDetailsUseCase(private val repository: LocationRepository) {
    suspend operator fun invoke(
        locationId: Int
    ): ApiResponse<LocationDetails> {
        val locationDetails = repository.getLocationDetails(locationId)
        if (locationDetails.isError()) {
            return ApiResponse.Error(message = locationDetails.message!!)
        }

        val nameToAreaResponse = coroutineScope {
            locationDetails.data!!.areas.map { area ->
                async(Dispatchers.IO) {
                    area.name to repository.getLocationArea(area.url.getIdFromUrl())
                }
            }.awaitAll().toMap()
        }

        val error = nameToAreaResponse.values.firstOrNull { it.isError() }
        if (error != null) {
            return ApiResponse.Error(message = error.message!!)
        }

        val nameToAreaDetails =
            nameToAreaResponse.mapValues { (_, response) -> response.data!! }
        return ApiResponse.Success(LocationDetails.from(locationDetails.data!!, nameToAreaDetails))

    }
}