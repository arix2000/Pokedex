package com.arix.pokedex.features.type_effectiveness.data

import com.arix.pokedex.extensions.getIdFromUrl
import com.arix.pokedex.features.locations.domain.model.details.location_details.NamedApiResource
import com.arix.pokedex.features.type_effectiveness.domain.model.RawTypeDetails
import com.arix.pokedex.features.type_effectiveness.domain.model.TypesResponse
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class TypeEffectivenessRepository(val remoteDataSource: TypeEffectivenessRemoteDataSource) {
    suspend fun getTypes(): ApiResponse<List<RawTypeDetails>> {
        val response: ApiResponse<TypesResponse> = remoteDataSource.getTypes()
        return when(response) {
            is ApiResponse.Success -> fetchAllTypes(response.data?.results!!)
            is ApiResponse.Error -> ApiResponse.Error(response.message ?: "")
        }
    }

    private suspend fun fetchAllTypes(data: List<NamedApiResource>): ApiResponse<List<RawTypeDetails>> =
        coroutineScope {
            val typeDetailsList = mutableListOf<RawTypeDetails>()
            val typesApiResponsesList: List<ApiResponse<RawTypeDetails>> = data.map { type ->
                async { remoteDataSource.getTypeDetails(type.url.getIdFromUrl()) }
            }.awaitAll()
            for(typeApiResponse in typesApiResponsesList) {
                when(typeApiResponse) {
                    is ApiResponse.Success -> typeDetailsList.add(typeApiResponse.data!!)
                    is ApiResponse.Error -> return@coroutineScope ApiResponse.Error(typeApiResponse.message ?: "")
                }
            }
            return@coroutineScope ApiResponse.Success(typeDetailsList)
        }
}
