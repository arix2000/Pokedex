package com.arix.pokedex.utils

sealed class ApiResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : ApiResponse<T>(data, message)

    fun <R> mapSuccess(converter: (T) -> R): ApiResponse<R> {
        return if (this is Success)
            Success(converter(data!!))
        else Error(message!!, null)
    }

    suspend fun <R> mapSuccessAsync(converter: suspend (T) -> R): ApiResponse<R> {
        return if (this is Success)
            Success(converter(data!!))
        else Error(message!!, null)
    }
}