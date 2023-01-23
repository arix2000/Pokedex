package com.arix.pokedex.extensions

import com.arix.pokedex.utils.Resource
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

fun <T> List<Resource<T>>.ifAllErrors(): Boolean {
    return all { it is Resource.Error }
}

fun <T> List<Resource<T>>.ifAllSuccess(): Boolean {
    return all { it is Resource.Success }
}
fun <T> List<Resource<T>>.ifAnyError(): Boolean {
    return any { it is Resource.Error }
}

fun <T> getTypeOf(): Type {
    return object : TypeToken<T>() {}.type
}