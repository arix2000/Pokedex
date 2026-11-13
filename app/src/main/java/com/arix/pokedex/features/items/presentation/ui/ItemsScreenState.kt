package com.arix.pokedex.features.items.presentation.ui

import com.arix.pokedex.features.items.domain.model.item_details.ItemDetails

data class ItemsScreenState(
    val isLoading: Boolean = false,
    val itemDetails: ItemDetails? = null,
    val errorMessage: String? = null
)