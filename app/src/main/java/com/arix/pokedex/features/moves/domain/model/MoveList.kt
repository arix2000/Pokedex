package com.arix.pokedex.features.moves.domain.model

import com.arix.pokedex.features.moves.domain.model.move_list.MoveLink
import com.google.gson.annotations.SerializedName

data class MoveList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val moves: List<Move>
) {
    companion object {
        fun from(raw: MoveListRaw, moves: List<Move>): MoveList {
            return MoveList(raw.count, raw.next, raw.previous, moves)
        }
    }
}