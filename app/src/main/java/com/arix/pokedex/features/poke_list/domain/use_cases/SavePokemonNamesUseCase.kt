package com.arix.pokedex.features.poke_list.domain.use_cases

import com.arix.pokedex.core.Constants.PokemonListScreen.POKEMON_LIST_ITEM_LIMIT
import com.arix.pokedex.features.poke_list.domain.model.list.PokemonList
import com.arix.pokedex.features.poke_list.domain.PokemonRepository
import com.arix.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

class SavePokemonNamesUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(names: List<String>) {
        return repository.savePokemonNames(names)
    }
}