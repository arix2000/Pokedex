package com.arix.pokedex.features.items.presentation.ui

sealed class ItemEvent {
    class GetItemDetails(val itemId: String): ItemEvent()
}