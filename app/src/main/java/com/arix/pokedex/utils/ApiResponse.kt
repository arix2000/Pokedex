package com.arix.pokedex.utils

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(message: String, exception: Exception? = null) :
        ApiResponse<T>(message = message, exception = exception)

    fun <R> mapSuccess(converter: (T) -> R): ApiResponse<R> {
        return if (this is Success)
            Success(converter(data!!))
        else Error(message!!)
    }

    suspend fun <R> mapSuccessAsync(converter: suspend (T) -> R): ApiResponse<R> {
        return if (this is Success)
            Success(converter(data!!))
        else Error(message!!)
    }

    fun isSuccess(): Boolean {
        return this is Success
    }

    fun isError(): Boolean {
        return this is Error
    }
}