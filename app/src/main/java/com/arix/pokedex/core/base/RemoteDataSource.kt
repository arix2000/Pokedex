package com.arix.pokedex.core.base

import com.arix.pokedex.utils.Resource

abstract class RemoteDataSource {

    suspend fun <T> makeHttpRequest(request: suspend () -> T): Resource<T> {
        val result = try {
            request.invoke()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "UnexpectedError")
        }
        return Resource.Success(result)
    }
}