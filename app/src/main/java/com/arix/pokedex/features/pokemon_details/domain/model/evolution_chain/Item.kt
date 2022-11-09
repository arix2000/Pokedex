package com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain

import com.arix.pokedex.core.Constants.PokemonDetailsScreen.ITEM_URL_PREFIX
import com.arix.pokedex.core.Constants.PokemonDetailsScreen.PNG_EXT

data class Item(
    val name: String,
    val url: String
) {
    fun getImageUrl(): String = ITEM_URL_PREFIX + name + PNG_EXT
}
