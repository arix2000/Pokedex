package com.arix.pokedex.features.move_details.domain.model

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale

data class UiStatChange(val change: Int, val statName: String) {
    fun getStatChangeText(): String {
        val changeText = if (change > 0) "+$change" else change
        val statNameFormatted =
            statName.replace("-", " ").replace("special", "sp.").capitalize(Locale.current)
        return "$statNameFormatted $changeText"
    }
}
