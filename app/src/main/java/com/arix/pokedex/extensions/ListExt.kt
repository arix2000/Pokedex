package com.arix.pokedex.extensions

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