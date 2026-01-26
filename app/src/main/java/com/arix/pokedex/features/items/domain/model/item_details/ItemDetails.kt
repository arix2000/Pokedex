package com.arix.pokedex.features.items.domain.model.item_details

import androidx.compose.ui.graphics.Color
import com.arix.pokedex.core.Constants.ItemsScreenConst.NO_EFFECT_STRING
import com.arix.pokedex.core.Constants.Language.ENGLISH_LANGUAGE_CODE
import com.arix.pokedex.extensions.clearEndOfLineEscapeSequences
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.domain.model.item_details.raw.Category
import com.arix.pokedex.features.items.domain.model.item_details.raw.EffectEntry
import com.arix.pokedex.features.items.domain.model.item_details.raw.Language
import com.arix.pokedex.features.items.domain.model.item_details.raw.RawItemDetails

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
) : Item(id, name, Category(categoryName)) {
    companion object {
        fun fromRaw(raw: RawItemDetails): ItemDetails {
            val errorText = "no data"
            with(raw) {
                val effectEntry =
                    effect_entries.lastOrNull { it.language.name == ENGLISH_LANGUAGE_CODE }
                        ?: EffectEntry(
                            errorText,
                            Language(errorText, errorText), errorText
                        )
                val coloredCategory = category.mapToColoredCategory()
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
                    flavor_text_entries.lastOrNull { it.language.name == ENGLISH_LANGUAGE_CODE }?.text?.clearEndOfLineEscapeSequences()
                        ?: errorText,
                    id,
                    name,
                    sprites.default ?: errorText,
                    coloredCategory.color
                )
            }
        }

    }
}