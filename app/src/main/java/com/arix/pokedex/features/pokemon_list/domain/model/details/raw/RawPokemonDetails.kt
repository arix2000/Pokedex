package com.arix.pokedex.features.pokemon_list.domain.model.details.raw

data class RawPokemonDetails(
    val abilities: List<RawAbility>,
    val height: Int,
    val id: Int,
    val location_area_encounters: String,
    val moves: List<MoveWrapper>,
    val name: String,
    val species: Species,
    val sprites: RawSprites,
    val stats: List<RawStat>,
    val types: List<RawType>,
    val weight: Int
)
