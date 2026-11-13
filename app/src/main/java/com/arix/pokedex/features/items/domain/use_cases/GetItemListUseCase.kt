package com.arix.pokedex.features.items.domain.use_cases

import com.arix.pokedex.core.Constants.ItemsScreenConst.ITEMS_LIMIT
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.items.data.ItemRepository
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.utils.ApiResponse

class GetItemListUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(
        offset: Int,
        limit: Int = ITEMS_LIMIT,
        searchQuery: String
    ): ApiResponse<Page<Item>> {
        return repository.getItems(limit, offset, searchQuery)
    }
}