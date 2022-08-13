package com.arix.pokedex.features.poke_list.domain.model.details

import com.arix.pokedex.core.Constants.UnitsOfMeasure.KG
import com.arix.pokedex.core.Constants.UnitsOfMeasure.M
import com.arix.pokedex.extensions.formatToUserFriendlyString

data class PokemonDetails(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<MoveWrapper>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {

    fun getHeightInMeters() = (height.toFloat() / 10).formatToUserFriendlyString() + M

    fun getWeightInKilograms() = (weight.toFloat() / 10).formatToUserFriendlyString() + KG

    companion object {
        val EMPTY = PokemonDetails(
            emptyList(), 1, emptyList(),
            emptyList(), 1, emptyList(), 1, false, "",
            emptyList(), "", 1, emptyList(), Species("", ""), Sprites.EMPTY,
            emptyList(), emptyList(), 1,
        )
    }
}