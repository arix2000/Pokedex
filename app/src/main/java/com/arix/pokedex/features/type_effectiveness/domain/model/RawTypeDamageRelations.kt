package com.arix.pokedex.features.type_effectiveness.domain.model

import com.arix.pokedex.features.locations.domain.model.details.location_details.NamedApiResource
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.google.gson.annotations.SerializedName

data class RawTypeDamageRelations(
    @SerializedName("double_damage_from")
    val doubleDamageFrom: List<NamedApiResource>,
    @SerializedName("half_damage_from")
    val halfDamageFrom: List<NamedApiResource>,
    @SerializedName("no_damage_from")
    val noDamageFrom: List<NamedApiResource>,

    @SerializedName("double_damage_to")
    val doubleDamageTo: List<NamedApiResource>,
    @SerializedName("half_damage_to")
    val halfDamageTo: List<NamedApiResource>,
    @SerializedName("no_damage_to")
    val noDamageTo: List<NamedApiResource>
) {
    fun mapToMultipliersToTypes(allTypes: List<Type>): Map<Type, DamageMultiplierCategory> {
        val multipliersMap = mutableMapOf<Type, DamageMultiplierCategory>()

        allTypes.forEach { multipliersMap[it] = DamageMultiplierCategory.NORMAL }

        doubleDamageFrom.forEach { multipliersMap[Type(it.name)] = DamageMultiplierCategory.DOUBLE }
        halfDamageFrom.forEach { multipliersMap[Type(it.name)] = DamageMultiplierCategory.HALF }
        noDamageFrom.forEach { multipliersMap[Type(it.name)] = DamageMultiplierCategory.NO_DAMAGE }

        return multipliersMap
    }
}