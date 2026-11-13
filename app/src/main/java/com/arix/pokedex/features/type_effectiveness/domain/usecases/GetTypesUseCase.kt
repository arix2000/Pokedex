package com.arix.pokedex.features.type_effectiveness.domain.usecases

import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.type_effectiveness.data.TypeEffectivenessRepository
import com.arix.pokedex.features.type_effectiveness.domain.model.RawTypeDetails
import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType
import com.arix.pokedex.features.type_effectiveness.domain.model.TypeEffectiveness
import com.arix.pokedex.utils.ApiResponse

class GetTypesUseCase(private val repository: TypeEffectivenessRepository) {
    suspend operator fun invoke(): ApiResponse<List<TypeEffectiveness>> {
        val typesResponse: ApiResponse<List<RawTypeDetails>> = repository.getTypes()
        return typesResponse.mapSuccess { types ->
            val allPokemonTypes = types.filterNot { listOf("unknown", "stellar", "shadow").contains(it.name) }
            val allTypes = allPokemonTypes.map { Type(it.name) }
            allPokemonTypes.map { type ->
                TypeEffectiveness(
                    type = SelectableType(type = Type(type.name)),
                    typesToMultipliers = type.damageRelations.mapToMultipliersToTypes(allTypes)
                )
            }
        }
    }
}