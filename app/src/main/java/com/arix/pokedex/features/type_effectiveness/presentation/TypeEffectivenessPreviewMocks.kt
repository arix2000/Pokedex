package com.arix.pokedex.features.type_effectiveness.presentation

import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.type_effectiveness.domain.model.DamageMultiplier
import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType
import com.arix.pokedex.features.type_effectiveness.domain.model.TypeEffectiveness

val mockTypeEffectivenessList = listOf(
    TypeEffectiveness(
        type = SelectableType(type = Type("Normal")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to emptyList(),
            DamageMultiplier.HALF to listOf(Type("Rock"), Type("Steel")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Ghost")),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Fire"), Type("Water"), Type("Grass"),
                Type("Electric"), Type("Ice"), Type("Fighting"), Type("Poison"),
                Type("Ground"), Type("Flying"), Type("Psychic"), Type("Bug"),
                Type("Dragon"), Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Fire")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Grass"), Type("Bug"), Type("Ice"), Type("Steel")),
            DamageMultiplier.HALF to listOf(Type("Fire"), Type("Water"), Type("Rock"), Type("Dragon")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Electric"), Type("Fighting"), Type("Poison"),
                Type("Ground"), Type("Flying"), Type("Psychic"), Type("Ghost"),
                Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Water")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Fire"), Type("Ground"), Type("Rock")),
            DamageMultiplier.HALF to listOf(Type("Water"), Type("Grass"), Type("Dragon")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Electric"), Type("Ice"), Type("Fighting"),
                Type("Poison"), Type("Flying"), Type("Psychic"), Type("Bug"),
                Type("Ghost"), Type("Dark"), Type("Steel"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Grass")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Water"), Type("Ground"), Type("Rock")),
            DamageMultiplier.HALF to listOf(
                Type("Fire"), Type("Grass"), Type("Poison"), Type("Flying"),
                Type("Bug"), Type("Dragon"), Type("Steel")
            ),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Electric"), Type("Ice"), Type("Fighting"),
                Type("Psychic"), Type("Ghost"), Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Electric")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Water"), Type("Flying")),
            DamageMultiplier.HALF to listOf(Type("Electric"), Type("Grass"), Type("Dragon")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Ground")),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Fire"), Type("Ice"), Type("Fighting"),
                Type("Poison"), Type("Psychic"), Type("Bug"), Type("Rock"),
                Type("Ghost"), Type("Dark"), Type("Steel"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Ice")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Grass"), Type("Ground"), Type("Flying"), Type("Dragon")),
            DamageMultiplier.HALF to listOf(Type("Fire"), Type("Water"), Type("Ice"), Type("Steel")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Electric"), Type("Fighting"), Type("Poison"),
                Type("Psychic"), Type("Bug"), Type("Rock"), Type("Ghost"),
                Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Fighting")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Normal"), Type("Ice"), Type("Rock"), Type("Dark"), Type("Steel")),
            DamageMultiplier.HALF to listOf(Type("Poison"), Type("Flying"), Type("Psychic"), Type("Bug"), Type("Fairy")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Ghost")),
            DamageMultiplier.NORMAL to listOf(
                Type("Fire"), Type("Water"), Type("Electric"), Type("Grass"),
                Type("Fighting"), Type("Ground"), Type("Dragon")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Poison")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Grass"), Type("Fairy")),
            DamageMultiplier.HALF to listOf(Type("Poison"), Type("Ground"), Type("Rock"), Type("Ghost")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Steel")),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Fire"), Type("Water"), Type("Electric"),
                Type("Ice"), Type("Fighting"), Type("Flying"), Type("Psychic"),
                Type("Bug"), Type("Dragon"), Type("Dark")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Ground")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Fire"), Type("Electric"), Type("Poison"), Type("Rock"), Type("Steel")),
            DamageMultiplier.HALF to listOf(Type("Grass"), Type("Bug")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Flying")),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Water"), Type("Ice"), Type("Fighting"),
                Type("Ground"), Type("Psychic"), Type("Ghost"), Type("Dragon"),
                Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Flying")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Grass"), Type("Fighting"), Type("Bug")),
            DamageMultiplier.HALF to listOf(Type("Electric"), Type("Rock"), Type("Steel")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Fire"), Type("Water"), Type("Ice"),
                Type("Poison"), Type("Ground"), Type("Flying"), Type("Psychic"),
                Type("Ghost"), Type("Dragon"), Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Psychic")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Fighting"), Type("Poison")),
            DamageMultiplier.HALF to listOf(Type("Psychic"), Type("Steel")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Dark")),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Fire"), Type("Water"), Type("Electric"),
                Type("Grass"), Type("Ice"), Type("Ground"), Type("Flying"),
                Type("Bug"), Type("Rock"), Type("Ghost"), Type("Dragon"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Bug")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Grass"), Type("Psychic"), Type("Dark")),
            DamageMultiplier.HALF to listOf(
                Type("Fire"), Type("Fighting"), Type("Poison"), Type("Flying"),
                Type("Ghost"), Type("Steel"), Type("Fairy")
            ),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Water"), Type("Electric"), Type("Ice"),
                Type("Ground"), Type("Bug"), Type("Rock"), Type("Dragon")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Rock")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Fire"), Type("Ice"), Type("Flying"), Type("Bug")),
            DamageMultiplier.HALF to listOf(Type("Fighting"), Type("Ground"), Type("Steel")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Water"), Type("Electric"), Type("Grass"),
                Type("Poison"), Type("Psychic"), Type("Rock"), Type("Ghost"),
                Type("Dragon"), Type("Dark"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Ghost")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Psychic"), Type("Ghost")),
            DamageMultiplier.HALF to listOf(Type("Dark")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Normal")),
            DamageMultiplier.NORMAL to listOf(
                Type("Fire"), Type("Water"), Type("Electric"), Type("Grass"),
                Type("Ice"), Type("Fighting"), Type("Poison"), Type("Ground"),
                Type("Flying"), Type("Bug"), Type("Rock"), Type("Dragon"),
                Type("Steel"), Type("Fairy")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Dragon")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Dragon")),
            DamageMultiplier.HALF to listOf(Type("Steel")),
            DamageMultiplier.NO_DAMAGE to listOf(Type("Fairy")),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Fire"), Type("Water"), Type("Electric"),
                Type("Grass"), Type("Ice"), Type("Fighting"), Type("Poison"),
                Type("Ground"), Type("Flying"), Type("Psychic"), Type("Bug"),
                Type("Rock"), Type("Ghost"), Type("Dark")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Dark")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Psychic"), Type("Ghost")),
            DamageMultiplier.HALF to listOf(Type("Fighting"), Type("Dark"), Type("Fairy")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Fire"), Type("Water"), Type("Electric"),
                Type("Grass"), Type("Ice"), Type("Poison"), Type("Ground"),
                Type("Flying"), Type("Bug"), Type("Rock"), Type("Dragon"), Type("Steel")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Steel")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Ice"), Type("Rock"), Type("Fairy")),
            DamageMultiplier.HALF to listOf(Type("Fire"), Type("Water"), Type("Electric"), Type("Steel")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Grass"), Type("Fighting"), Type("Poison"),
                Type("Ground"), Type("Flying"), Type("Psychic"), Type("Bug"),
                Type("Ghost"), Type("Dragon"), Type("Dark")
            )
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Fairy")),
        multipliersToTypes = mapOf(
            DamageMultiplier.DOUBLE to listOf(Type("Fighting"), Type("Dragon"), Type("Dark")),
            DamageMultiplier.HALF to listOf(Type("Fire"), Type("Poison"), Type("Steel")),
            DamageMultiplier.NO_DAMAGE to emptyList(),
            DamageMultiplier.NORMAL to listOf(
                Type("Normal"), Type("Water"), Type("Electric"), Type("Grass"),
                Type("Ice"), Type("Ground"), Type("Flying"), Type("Psychic"),
                Type("Bug"), Type("Rock"), Type("Ghost"), Type("Fairy")
            )
        )
    )
)