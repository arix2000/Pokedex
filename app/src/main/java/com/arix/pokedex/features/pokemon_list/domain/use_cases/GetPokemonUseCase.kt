package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.utils.ApiResponse

class GetPokemonUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(name: String): ApiResponse<PokemonDetails> {
        return repository.getPokemon(name).mapSuccess { PokemonDetails.fromRaw(it) }
    }
}