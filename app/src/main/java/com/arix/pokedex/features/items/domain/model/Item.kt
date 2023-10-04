package com.arix.pokedex.features.items.domain.model

import com.arix.pokedex.features.items.domain.model.item_details.raw.Category

open class Item(open val id: Int, open val name: String, open val category: Category) {
    companion object {
        val EMPTY = Item(-1, "", Category(""))
    }
}