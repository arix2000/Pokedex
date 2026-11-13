package com.arix.pokedex.features.pokemon_list.domain.model.list

import com.arix.pokedex.core.Constants
import com.arix.pokedex.core.Constants.PokemonDetailsScreen.PNG_EXT
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.google.gson.annotations.SerializedName

open class PokemonItem(
    open val id: Int,
    open val name: String,
    open val types: List<Type>,
    @SerializedName("image_url") open val imageUrl: String
)