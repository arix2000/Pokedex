package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository

class SavePokemonNamesUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(names: List<String>) {
        return repository.savePokemonNames(names)
    }
}