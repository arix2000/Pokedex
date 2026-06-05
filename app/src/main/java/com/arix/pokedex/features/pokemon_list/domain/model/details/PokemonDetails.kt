package com.arix.pokedex.features.pokemon_list.domain.model.details

import com.arix.pokedex.core.Constants.UnitsOfMeasure.KG
import com.arix.pokedex.core.Constants.UnitsOfMeasure.M
import com.arix.pokedex.extensions.formatToUserFriendlyString
import com.arix.pokedex.features.locations.domain.model.list.LocationItem
import com.arix.pokedex.features.moves.domain.model.MoveItem
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.RawPokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.model.details.raw.Species
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem

data class PokemonDetails(
    val abilities: List<Ability>,
    val height: Int,
    override val id: Int,
    val locations: List<LocationItem>,
    val allLocationsNames: List<String>,
    val cryUrl: String,
    val moves: List<MoveItem>,
    val allMovesNames: List<String>,
    override val name: String,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    override val types: List<Type>,
    val weight: Int
) : PokemonItem(id, name, types, sprites.front_default) {

    fun getHeightInMeters() = (height.toFloat() / 10).formatToUserFriendlyString() + M

    fun getWeightInKilograms() = (weight.toFloat() / 10).formatToUserFriendlyString() + KG

    companion object {
        val EMPTY = PokemonDetails(
            emptyList(), 1, 1, emptyList(), emptyList(), "",
            emptyList(), emptyList(), "", Species("", ""), Sprites("", ""),
            emptyList(), emptyList(), 1,
        )

        fun fromRaw(
            raw: RawPokemonDetails,
            abilities: List<Ability>,
            locations: List<LocationItem>,
            moves: List<MoveItem>,
            allLocationNames: List<String>
        ): PokemonDetails {
            with(raw) {
                return PokemonDetails(
                    abilities,
                    height,
                    id,
                    locations,
                    allLocationNames,
                    cries.latest ?: cries.legacy ?: "",
                    moves,
                    raw.moves.map { it.move.name },
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