package com.arix.pokedex.extensions

import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.EvolutionDetail

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

fun <T> List<T>.isFirstElement(element: T): Boolean {
    return 0 == indexOf(element)
}