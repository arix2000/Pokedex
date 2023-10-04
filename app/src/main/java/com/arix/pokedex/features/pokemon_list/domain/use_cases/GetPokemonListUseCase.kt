package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.core.Constants.PokemonListScreen.POKEMON_LIST_ITEM_LIMIT
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.utils.ApiResponse

class GetPokemonListUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(
        offset: Int,
        searchQuery: String,
        limit: Int = POKEMON_LIST_ITEM_LIMIT
    ): ApiResponse<Page<PokemonItem>> {
        return repository.getPokemonList(offset, searchQuery, limit)
    }
}