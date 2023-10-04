package com.arix.pokedex.features.items.domain.model.item_details.raw

import com.arix.pokedex.features.items.domain.model.item_details.ColoredCategory
import com.arix.pokedex.features.items.domain.model.item_details.ItemCategories
import com.arix.pokedex.theme.ItemCategoryColors

data class Category(
    val name: String,
) {
    fun mapCategoryToColoredCategory(): ColoredCategory {
        return when {
            ItemCategories.pokeBalls.contains(name) -> ColoredCategory("Pokeball", ItemCategoryColors.pokeBalls)
            ItemCategories.medicine.contains(name) -> ColoredCategory("Medicine", ItemCategoryColors.medicine)
            ItemCategories.machines.contains(name) -> ColoredCategory("TM", ItemCategoryColors.allMachines)
            ItemCategories.berries.contains(name) -> ColoredCategory("Berry", ItemCategoryColors.berries)
            ItemCategories.mail.contains(name) -> ColoredCategory("Mail", ItemCategoryColors.mail)
            ItemCategories.battle.contains(name) -> ColoredCategory("Battle item", ItemCategoryColors.battle)
            ItemCategories.key.contains(name) -> ColoredCategory("Key item", ItemCategoryColors.key)
            ItemCategories.items.contains(name) -> ColoredCategory("Item", ItemCategoryColors.items)
            else -> ColoredCategory("Item", ItemCategoryColors.items)
        }
    }
}