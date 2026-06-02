package com.arix.pokedex.features.items.domain.model

import com.arix.pokedex.features.items.domain.model.item_details.raw.Category
import com.arix.pokedex.features.items.domain.model.item_details.raw.Sprites

open class Item(open val id: Int, open val name: String, open val category: Category, open val sprites: Sprites) {
    companion object {
        val EMPTY = Item(-1, "", Category(""), Sprites(""))
    }
}