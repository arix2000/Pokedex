package com.arix.pokedex.features.pokemon_details.domain.model.evolution_chain

import com.arix.pokedex.extensions.getIdFromUrl
import com.arix.pokedex.features.pokemon_details.domain.model.RawEvolutionStep

data class PokemonEvolutionChain(
    val baby_trigger_item: Any,
    val chain: Chain,
    val id: Int
) {
    fun getPokemonEvolutionSteps(): List<RawEvolutionStep> {
        val evolutionAmount = when {
            chain.evolves_to.isEmpty() -> 1
            chain.evolves_to.first().evolves_to.isEmpty() -> 2
            chain.evolves_to.first().evolves_to.first().evolves_to.isEmpty() -> 3
            else -> 0
        }

        return mutableListOf<RawEvolutionStep>().apply {
            when (evolutionAmount) {
                1 -> add(RawEvolutionStep(mapOf(chain.species.url.getIdFromUrl() to chain.evolution_details)))
                2 -> {
                    add(RawEvolutionStep(mapOf(chain.species.url.getIdFromUrl() to chain.evolution_details)))
                    add(RawEvolutionStep(getEvolutionMapFromFirstEvolution()))
                }
                3 -> {
                    add(RawEvolutionStep(mapOf(chain.species.url.getIdFromUrl() to chain.evolution_details)))
                    add(RawEvolutionStep(getEvolutionMapFromFirstEvolution()))
                    add(RawEvolutionStep(getEvolutionMapFromSecondEvolution()))
                }
            }
        }
    }

    private fun getEvolutionMapFromFirstEvolution(): Map<Int, List<EvolutionDetail>> {
        return chain.evolves_to.associate { it.species.url.getIdFromUrl() to it.evolution_details }
    }

    private fun getEvolutionMapFromSecondEvolution(): Map<Int, List<EvolutionDetail>> {
        val pokemonEvolutionMap: MutableMap<Int, List<EvolutionDetail>> = mutableMapOf()
        chain.evolves_to.forEach { previousEvolution ->
            with(previousEvolution.evolves_to) {
                pokemonEvolutionMap.putAll(associate { it.species.url.getIdFromUrl() to it.evolution_details })
            }
        }
        return pokemonEvolutionMap
    }
}