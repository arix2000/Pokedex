package com.arix.pokedex.features.items.domain.use_cases

import com.arix.pokedex.features.items.data.ItemRepository
import com.arix.pokedex.features.items.domain.model.ItemList
import com.arix.pokedex.utils.ApiResponse

class GetItemListUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(offset: Int, limit: Int): ApiResponse<ItemList> {
        return repository.getItems(limit, offset)
    }
}