package com.arix.pokedex.extensions

fun String.withArgument(argumentName: String): String {
    return "$this/{$argumentName}"
}

fun String.putArgument(argumentName: String, argumentValue: String): String {
    return this.replace("{$argumentName}", argumentValue)
}

fun String.clearEndOfLineEscapeSequences(): String {
    return this.replace("\n", " ").replace("\\s+".toRegex(), " ")
}