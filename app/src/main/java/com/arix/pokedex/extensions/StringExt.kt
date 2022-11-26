package com.arix.pokedex.extensions

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale

fun String.withArgument(argumentName: String): String {
    return "$this/{$argumentName}"
}

fun String.putArgument(argumentName: String, argumentValue: String): String {
    return this.replace("{$argumentName}", argumentValue)
}

fun String.clearEndOfLineEscapeSequences(): String {
    return this.replace("\n", " ").replace("\\s+".toRegex(), " ")
}

fun String.getIdFromUrl(): Int {
    return this.dropLast(1).takeLastWhile { it.isDigit() }.toInt()
}

fun List<String>.filterAndSortListBy(query: String): List<String> {
    val filteredList = this.filter { it.contains(query) }
    val filteredAndSorted = filteredList
        .filter { it.startsWith(query) }
        .toMutableList()
    filteredList.forEach {
        if (!filteredAndSorted.contains(it)) filteredAndSorted.add(it)
    }
    return filteredAndSorted
}

fun String.toSentenceCase() = capitalize(Locale.current).replace("-", " ")