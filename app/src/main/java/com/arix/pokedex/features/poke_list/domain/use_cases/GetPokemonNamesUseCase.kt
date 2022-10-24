package com.arix.pokedex.features.poke_list.domain.use_cases

import com.arix.pokedex.features.poke_list.domain.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonNamesUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(): Flow<List<String>> {
        return repository.getPokemonNames()
    }
}