package com.arix.pokedex.core.base

import android.util.Log
import com.arix.pokedex.utils.ApiResponse
import kotlin.coroutines.cancellation.CancellationException

abstract class RemoteDataSource {

    suspend fun <T> makeHttpRequest(request: suspend () -> T): ApiResponse<T> {
        val result = try {
            request.invoke()
        } catch (e: Exception) {
            Log.e("SERVER_ERROR", e.stackTraceToString())
            return ApiResponse.Error(e.message ?: "UnexpectedError", exception = e)
        }
        return ApiResponse.Success(result)
    }
}