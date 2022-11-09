package com.arix.pokedex.features.pokemon_details.domain.use_cases

import com.arix.pokedex.features.pokemon_details.domain.PokemonDetailsRepository
import com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain.PokemonEvolutionChain
import com.arix.pokedex.utils.Resource

class GetPokemonEvolutionChainUseCase(private val repository: PokemonDetailsRepository) {
    suspend operator fun invoke(evolutionChainId: Int): Resource<PokemonEvolutionChain> {
        return repository.getPokemonEvolutionChain(evolutionChainId)
    }
}