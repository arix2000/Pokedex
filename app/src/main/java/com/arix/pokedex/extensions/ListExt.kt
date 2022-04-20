package com.arix.pokedex.extensions

fun <T> List<T>?.isSizeEqualsOrGreaterThan(otherList: List<T>) =
    (this?.size ?: 0) >= otherList.size