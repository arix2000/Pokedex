package com.arix.pokedex.extensions

import com.arix.pokedex.utils.ApiResponse
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun <T> List<T>?.isSizeEqualsOrGreaterThan(otherList: List<T>) =
    (this?.size ?: 0) >= otherList.size

fun <T> List<T>?.hasOneItem(): Boolean {
    return (this?.size ?: 0) == 1
}

fun <T> List<T>.isLastElement(element: T): Boolean {
    return this.lastIndex == indexOf(element)
}

fun <T> List<T>.isNotFirstElement(element: T): Boolean {
    return 0 != indexOf(element)
}

fun <T> List<ApiResponse<T>>.ifAllErrors(): Boolean {
    return all { it is ApiResponse.Error }
}

fun <T> List<ApiResponse<T>>.ifAllSuccess(): Boolean {
    return all { it is ApiResponse.Success }
}
fun <T> List<ApiResponse<T>>.ifAnyError(): Boolean {
    return any { it is ApiResponse.Error }
}

fun <T> getTypeOf(): Type {
    return object : TypeToken<T>() {}.type
}