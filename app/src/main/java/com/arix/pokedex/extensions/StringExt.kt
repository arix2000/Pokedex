package com.arix.pokedex.extensions

import java.util.regex.Pattern

fun String.withArgument(argumentName: String): String {
    return "$this/{$argumentName}"
}

fun String.putArgument(argumentName: String, argumentValue: String): String {
    return this.replace("{$argumentName}", argumentValue)
}

fun String.clearEndOfLineEscapeSequences(): String {
    return this.replace("\n", " ").replace("\\s+".toRegex(), " ")
}

fun String.countOccurrencesOf(string: String): Int {
    val matcher = Pattern.compile(string).matcher(this)
    var counter = 0
    while (matcher.find())
        counter++
    return counter
}