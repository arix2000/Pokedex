package com.arix.pokedex.features.pokemon_list.domain.model.details

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup
)