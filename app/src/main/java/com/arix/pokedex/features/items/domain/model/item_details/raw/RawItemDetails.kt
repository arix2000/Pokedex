package com.arix.pokedex.features.items.domain.model.item_details.raw

data class RawItemDetails(
    val attributes: List<Attribute>,
    val category: Category,
    val cost: Int,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntry>,
    val id: Int,
    val name: String,
    val sprites: Sprites
)