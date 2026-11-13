package com.arix.pokedex.extensions

import java.text.NumberFormat

fun Float.formatToUserFriendlyString(): String = NumberFormat.getInstance().format(this)

fun Int.getPokemonGenderRatio() = this.toFloat() / 8

private fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

fun Double.toFraction(): Pair<Long, Long> {
    val stringValue = this.toString()
    val decimalPlaces = stringValue.substringAfter('.', "").length

    var multiplier = 1L
    repeat(decimalPlaces) {
        multiplier *= 10
    }

    val numerator = (this * multiplier).toLong()
    val denominator = multiplier

    val divisor = gcd(numerator, denominator)

    return Pair(numerator / divisor, denominator / divisor)
}