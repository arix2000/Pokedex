package com.arix.pokedex.features.items.domain.use_cases

import com.arix.pokedex.features.items.data.ItemRepository

class SaveItemNamesUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(names: List<String>) {
        return repository.saveItemNames(names)
    }
}