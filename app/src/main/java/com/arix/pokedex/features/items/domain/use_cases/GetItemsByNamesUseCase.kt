package com.arix.pokedex.features.items.domain.use_cases

import com.arix.pokedex.features.items.data.ItemRepository
import com.arix.pokedex.features.items.domain.model.move_details.ItemDetails
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class GetItemsByNamesUseCase(private val repository: ItemRepository) {

    suspend operator fun invoke(names: List<String>): List<ApiResponse<ItemDetails>> {
        val itemResponses = names
            .map { CoroutineScope(Dispatchers.IO).async { repository.getItem(it) } }
        return itemResponses.awaitAll()
            .mapIndexed { index, response -> response.mapSuccessAsync { ItemDetails.fromRaw(it) } }
    }
}