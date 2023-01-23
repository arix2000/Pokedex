package com.arix.pokedex.features.pokemon_list.domain.use_cases

import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.domain.PokemonRepository
import com.arix.pokedex.utils.Resource

class GetPokemonUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(name: String): Resource<PokemonDetails> {
        return repository.getPokemon(name)
    }
}