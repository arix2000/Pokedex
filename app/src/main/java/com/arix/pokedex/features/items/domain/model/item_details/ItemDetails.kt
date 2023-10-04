package com.arix.pokedex.features.items.domain.model.item_details

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.core.Constants.ItemsScreenConst.NO_EFFECT_STRING
import com.arix.pokedex.core.Constants.Language.ENGLISH_LANGUAGE_CODE
import com.arix.pokedex.extensions.clearEndOfLineEscapeSequences
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.domain.model.item_details.raw.Category
import com.arix.pokedex.features.items.domain.model.item_details.raw.RawItemDetails
import com.arix.pokedex.theme.ItemCategoryColors

data class ItemDetails(
    val attributes: List<String>,
    val categoryName: String,
    val cost: Int,
    val effectTitle: String,
    val effectText: String,
    val shortEffectText: String,
    val flavorText: String,
    override val id: Int,
    override val name: String,
    val imageUrl: String,
    val categoryColor: Color
): Item(id, name, Category(categoryName)) {
    companion object {
        fun fromRaw(raw: RawItemDetails): ItemDetails {
            with(raw) {
                val effectEntry = effect_entries.last { it.language.name == ENGLISH_LANGUAGE_CODE }
                val coloredCategory = category.mapCategoryToColoredCategory()
                return ItemDetails(
                    attributes.map { it.name },
                    coloredCategory.name,
                    cost,
                    if (!effectEntry.effect.contains(NO_EFFECT_STRING)) effectEntry.effect.clearEndOfLineEscapeSequences()
                        .substringBefore(":").trim() else NO_EFFECT_STRING,
                    effectEntry.effect.clearEndOfLineEscapeSequences()
                        .substringAfter(if (effectEntry.effect.contains(NO_EFFECT_STRING)) NO_EFFECT_STRING else ":")
                        .trim(),
                    effectEntry.short_effect,
                    flavor_text_entries.last { it.language.name == ENGLISH_LANGUAGE_CODE }.text.clearEndOfLineEscapeSequences(),
                    id,
                    name,
                    sprites.default,
                    coloredCategory.color
                )
            }
        }

        private fun mapCategoryToColoredCategory(categoryName: String): ColoredCategory {
            return when {
                ItemCategories.pokeBalls.contains(categoryName) -> ColoredCategory("Pokeball", ItemCategoryColors.pokeBalls)
                ItemCategories.medicine.contains(categoryName) -> ColoredCategory("Medicine", ItemCategoryColors.medicine)
                ItemCategories.machines.contains(categoryName) -> ColoredCategory("TM", ItemCategoryColors.allMachines)
                ItemCategories.berries.contains(categoryName) -> ColoredCategory("Berry", ItemCategoryColors.berries)
                ItemCategories.mail.contains(categoryName) -> ColoredCategory("Mail", ItemCategoryColors.mail)
                ItemCategories.battle.contains(categoryName) -> ColoredCategory("Battle item", ItemCategoryColors.battle)
                ItemCategories.key.contains(categoryName) -> ColoredCategory("Key item", ItemCategoryColors.key)
                ItemCategories.items.contains(categoryName) -> ColoredCategory("Item", ItemCategoryColors.items)
                else -> ColoredCategory("Item", ItemCategoryColors.items)
            }
        }

        val EMPTY = ItemDetails(emptyList(), "", 0, "", "", "", "", -1, "", "", Color.Black)
    }
}