package com.arix.pokedex.features.poke_list.domain.model.details

data class Sprites(
    val back_default: String,
    val back_female: Any,
    val back_shiny: String,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any,
    val other: Other
) {
    companion object {
        val EMPTY = Sprites(
            "", "", "", "",
            "", "", "", "",
            Other(
                DreamWorld("", ""),
                Home("", "", "", ""),
                OfficialArtwork("")
            )
        )
    }
}