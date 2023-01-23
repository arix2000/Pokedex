package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.core.Constants.PokemonListScreen.POKEMON_LIST_ITEM_LIMIT
import com.arix.pokedex.features.pokemon_list.domain.model.list.PokemonList
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.utils.Resource

class GetPokemonListUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(
        offset: Int,
        limit: Int = POKEMON_LIST_ITEM_LIMIT
    ): Resource<PokemonList> {
        return repository.getPokemonList(offset, limit)
    }
}