package com.arix.pokedex.features.pokemon_list.domain.model.details

import com.arix.pokedex.features.moves.domain.model.move_list.MoveLink

data class MoveWrapper(
    val move: MoveLink,
    val version_group_details: List<VersionGroupDetail>
)