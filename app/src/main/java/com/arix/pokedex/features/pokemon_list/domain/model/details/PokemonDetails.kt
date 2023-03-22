package com.arix.pokedex.features.pokemon_list.domain.model.details

import com.arix.pokedex.core.Constants.UnitsOfMeasure.KG
import com.arix.pokedex.core.Constants.UnitsOfMeasure.M
import com.arix.pokedex.extensions.formatToUserFriendlyString
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.*

data class PokemonDetails(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val locationAreaEncounters: String,
    val moves: List<String>,
    val name: String,
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
            emptyList(), 1, 1, "",
            emptyList(), "", Species("", ""), Sprites("", ""),
            emptyList(), emptyList(), 1,
        )

        fun fromRaw(raw: RawPokemonDetails): PokemonDetails {
            with(raw) {
                return PokemonDetails(
                    abilities.map { Ability(it.ability.name, it.ability.url, it.is_hidden) },
                    height,
                    id,
                    location_area_encounters,
                    moves.map { it.move.name },
                    name,
                    species,
                    Sprites(
                        sprites.other.official_artwork.front_default,
                        sprites.other.official_artwork.front_shiny
                    ),
                    stats.map { Stat(it.base_stat, it.effort, it.stat.name) },
                    types.map { Type(it.type.name) },
                    weight
                )
            }
        }
    }
}