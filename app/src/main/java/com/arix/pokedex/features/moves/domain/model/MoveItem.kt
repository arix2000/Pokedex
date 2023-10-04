package com.arix.pokedex.features.moves.domain.model

import com.arix.pokedex.features.pokemon_list.domain.model.details.Type

open class MoveItem(open val id: Int, open val name: String, open val type: Type)
