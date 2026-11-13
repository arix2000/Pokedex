package com.arix.pokedex.features.type_effectiveness.data

import com.arix.pokedex.core.base.RemoteDataSource
import com.arix.pokedex.core.network.PokeApiService
import com.arix.pokedex.features.type_effectiveness.domain.model.RawTypeDetails
import com.arix.pokedex.features.type_effectiveness.domain.model.TypesResponse
import com.arix.pokedex.utils.ApiResponse

class TypeEffectivenessRemoteDataSource(val pokeApiService: PokeApiService) : RemoteDataSource() {
    suspend fun getTypes(): ApiResponse<TypesResponse> {
        return makeHttpRequest { pokeApiService.getTypes() }
    }

    suspend fun getTypeDetails(id: Int): ApiResponse<RawTypeDetails> {
        return makeHttpRequest { pokeApiService.getType(id) }
    }
}
