package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.core.Constants.PokemonListScreen.SINGLE_PAGE_BY_NAMES_LIMIT
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonItem
import com.arix.pokedex.utils.ApiResponse

class GetPokemonListByNamesUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(names: List<String>): ApiResponse<List<PokemonItem>> {
        return repository.getPokemonList(
            0,
            "",
            limit = SINGLE_PAGE_BY_NAMES_LIMIT,
            limitedList = names
        ).mapSuccess {
            it.items
        }
    }
}