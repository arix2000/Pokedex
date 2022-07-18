package com.arix.pokedex.features.poke_list.domain.model.details

data class MoveWrapper(
    val move: Move,
    val version_group_details: List<VersionGroupDetail>
)