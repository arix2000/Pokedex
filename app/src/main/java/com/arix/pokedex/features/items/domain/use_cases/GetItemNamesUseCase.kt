package com.arix.pokedex.features.items.domain.use_cases

import com.arix.pokedex.features.items.data.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetItemNamesUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(): Flow<List<String>> {
        return repository.getItemNames()
    }
}