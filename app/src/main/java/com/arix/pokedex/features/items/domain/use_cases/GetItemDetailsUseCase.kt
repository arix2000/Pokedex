package com.arix.pokedex.features.items.domain.use_cases

import com.arix.pokedex.features.items.data.ItemRepository
import com.arix.pokedex.features.items.domain.model.item_details.ItemDetails
import com.arix.pokedex.utils.ApiResponse

class GetItemDetailsUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(itemId: String): ApiResponse<ItemDetails> {
        return repository.getItem(itemId).mapSuccess { ItemDetails.fromRaw(it) }
    }
}