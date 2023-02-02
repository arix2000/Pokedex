package com.arix.pokedex.features.moves.domain.model

import com.arix.pokedex.features.moves.domain.model.move_list.MoveLink
import com.google.gson.annotations.SerializedName

data class MoveList(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val moveLinks: List<MoveLink>
)