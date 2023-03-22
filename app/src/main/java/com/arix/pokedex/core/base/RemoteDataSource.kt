package com.arix.pokedex.core.base

import android.util.Log
import com.arix.pokedex.utils.ApiResponse

abstract class RemoteDataSource {

    suspend fun <T> makeHttpRequest(request: suspend () -> T): ApiResponse<T> {
        val result = try {
            request.invoke()
        } catch (e: Exception) {
            Log.e("SERVER_ERROR", e.stackTraceToString())
            return ApiResponse.Error(e.message ?: "UnexpectedError")
        }
        return ApiResponse.Success(result)
    }
}