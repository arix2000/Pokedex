package com.arix.pokedex.features.poke_list.domain.model.details

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)