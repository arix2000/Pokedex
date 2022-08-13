package com.arix.pokedex.extensions

import java.text.NumberFormat

fun Float.formatToUserFriendlyString(): String = NumberFormat.getInstance().format(this)

fun Int.getPokemonGenderRatio() = this.toFloat() / 8