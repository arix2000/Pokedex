package com.arix.pokedex.features.type_effectiveness.presentation

import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.features.type_effectiveness.domain.model.DamageMultiplierCategory
import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType
import com.arix.pokedex.features.type_effectiveness.domain.model.TypeEffectiveness
import com.arix.pokedex.features.type_effectiveness.domain.model.TypesWithMultiplier

val mockTypeEffectivenessList = listOf(
    TypeEffectiveness(
        type = SelectableType(type = Type("Normal")),
        typesToMultipliers = mapOf(
            Type("Rock") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Ghost") to DamageMultiplierCategory.NO_DAMAGE,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Fire")),
        typesToMultipliers = mapOf(
            Type("Grass") to DamageMultiplierCategory.DOUBLE,
            Type("Bug") to DamageMultiplierCategory.DOUBLE,
            Type("Ice") to DamageMultiplierCategory.DOUBLE,
            Type("Steel") to DamageMultiplierCategory.DOUBLE,
            Type("Fire") to DamageMultiplierCategory.HALF,
            Type("Water") to DamageMultiplierCategory.HALF,
            Type("Rock") to DamageMultiplierCategory.HALF,
            Type("Dragon") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Water")),
        typesToMultipliers = mapOf(
            Type("Fire") to DamageMultiplierCategory.DOUBLE,
            Type("Ground") to DamageMultiplierCategory.DOUBLE,
            Type("Rock") to DamageMultiplierCategory.DOUBLE,
            Type("Water") to DamageMultiplierCategory.HALF,
            Type("Grass") to DamageMultiplierCategory.HALF,
            Type("Dragon") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Steel") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Grass")),
        typesToMultipliers = mapOf(
            Type("Water") to DamageMultiplierCategory.DOUBLE,
            Type("Ground") to DamageMultiplierCategory.DOUBLE,
            Type("Rock") to DamageMultiplierCategory.DOUBLE,
            Type("Fire") to DamageMultiplierCategory.HALF,
            Type("Grass") to DamageMultiplierCategory.HALF,
            Type("Poison") to DamageMultiplierCategory.HALF,
            Type("Flying") to DamageMultiplierCategory.HALF,
            Type("Bug") to DamageMultiplierCategory.HALF,
            Type("Dragon") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Electric"), isSelected = true),
        typesToMultipliers = mapOf(
            Type("Water") to DamageMultiplierCategory.DOUBLE,
            Type("Flying") to DamageMultiplierCategory.DOUBLE,
            Type("Electric") to DamageMultiplierCategory.HALF,
            Type("Grass") to DamageMultiplierCategory.HALF,
            Type("Dragon") to DamageMultiplierCategory.HALF,
            Type("Ground") to DamageMultiplierCategory.NO_DAMAGE,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Steel") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Ice")),
        typesToMultipliers = mapOf(
            Type("Grass") to DamageMultiplierCategory.DOUBLE,
            Type("Ground") to DamageMultiplierCategory.DOUBLE,
            Type("Flying") to DamageMultiplierCategory.DOUBLE,
            Type("Dragon") to DamageMultiplierCategory.DOUBLE,
            Type("Fire") to DamageMultiplierCategory.HALF,
            Type("Water") to DamageMultiplierCategory.HALF,
            Type("Ice") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Fighting")),
        typesToMultipliers = mapOf(
            Type("Normal") to DamageMultiplierCategory.DOUBLE,
            Type("Ice") to DamageMultiplierCategory.DOUBLE,
            Type("Rock") to DamageMultiplierCategory.DOUBLE,
            Type("Dark") to DamageMultiplierCategory.DOUBLE,
            Type("Steel") to DamageMultiplierCategory.DOUBLE,
            Type("Poison") to DamageMultiplierCategory.HALF,
            Type("Flying") to DamageMultiplierCategory.HALF,
            Type("Psychic") to DamageMultiplierCategory.HALF,
            Type("Bug") to DamageMultiplierCategory.HALF,
            Type("Fairy") to DamageMultiplierCategory.HALF,
            Type("Ghost") to DamageMultiplierCategory.NO_DAMAGE,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Poison")),
        typesToMultipliers = mapOf(
            Type("Grass") to DamageMultiplierCategory.DOUBLE,
            Type("Fairy") to DamageMultiplierCategory.DOUBLE,
            Type("Poison") to DamageMultiplierCategory.HALF,
            Type("Ground") to DamageMultiplierCategory.HALF,
            Type("Rock") to DamageMultiplierCategory.HALF,
            Type("Ghost") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.NO_DAMAGE,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Ground")),
        typesToMultipliers = mapOf(
            Type("Fire") to DamageMultiplierCategory.DOUBLE,
            Type("Electric") to DamageMultiplierCategory.DOUBLE,
            Type("Poison") to DamageMultiplierCategory.DOUBLE,
            Type("Rock") to DamageMultiplierCategory.DOUBLE,
            Type("Steel") to DamageMultiplierCategory.DOUBLE,
            Type("Grass") to DamageMultiplierCategory.HALF,
            Type("Bug") to DamageMultiplierCategory.HALF,
            Type("Flying") to DamageMultiplierCategory.NO_DAMAGE,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Flying")),
        typesToMultipliers = mapOf(
            Type("Grass") to DamageMultiplierCategory.DOUBLE,
            Type("Fighting") to DamageMultiplierCategory.DOUBLE,
            Type("Bug") to DamageMultiplierCategory.DOUBLE,
            Type("Electric") to DamageMultiplierCategory.HALF,
            Type("Rock") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Psychic")),
        typesToMultipliers = mapOf(
            Type("Fighting") to DamageMultiplierCategory.DOUBLE,
            Type("Poison") to DamageMultiplierCategory.DOUBLE,
            Type("Psychic") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Dark") to DamageMultiplierCategory.NO_DAMAGE,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Bug")),
        typesToMultipliers = mapOf(
            Type("Grass") to DamageMultiplierCategory.DOUBLE,
            Type("Psychic") to DamageMultiplierCategory.DOUBLE,
            Type("Dark") to DamageMultiplierCategory.DOUBLE,
            Type("Fire") to DamageMultiplierCategory.HALF,
            Type("Fighting") to DamageMultiplierCategory.HALF,
            Type("Poison") to DamageMultiplierCategory.HALF,
            Type("Flying") to DamageMultiplierCategory.HALF,
            Type("Ghost") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Fairy") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Rock")),
        typesToMultipliers = mapOf(
            Type("Fire") to DamageMultiplierCategory.DOUBLE,
            Type("Ice") to DamageMultiplierCategory.DOUBLE,
            Type("Flying") to DamageMultiplierCategory.DOUBLE,
            Type("Bug") to DamageMultiplierCategory.DOUBLE,
            Type("Fighting") to DamageMultiplierCategory.HALF,
            Type("Ground") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Ghost")),
        typesToMultipliers = mapOf(
            Type("Psychic") to DamageMultiplierCategory.DOUBLE,
            Type("Ghost") to DamageMultiplierCategory.DOUBLE,
            Type("Dark") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NO_DAMAGE,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Steel") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Dragon")),
        typesToMultipliers = mapOf(
            Type("Dragon") to DamageMultiplierCategory.DOUBLE,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Fairy") to DamageMultiplierCategory.NO_DAMAGE,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Dark")),
        typesToMultipliers = mapOf(
            Type("Psychic") to DamageMultiplierCategory.DOUBLE,
            Type("Ghost") to DamageMultiplierCategory.DOUBLE,
            Type("Fighting") to DamageMultiplierCategory.HALF,
            Type("Dark") to DamageMultiplierCategory.HALF,
            Type("Fairy") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Fire") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Steel") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Steel"), isSelected = true),
        typesToMultipliers = mapOf(
            Type("Ice") to DamageMultiplierCategory.DOUBLE,
            Type("Rock") to DamageMultiplierCategory.DOUBLE,
            Type("Fairy") to DamageMultiplierCategory.DOUBLE,
            Type("Fire") to DamageMultiplierCategory.HALF,
            Type("Water") to DamageMultiplierCategory.HALF,
            Type("Electric") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Fighting") to DamageMultiplierCategory.NORMAL,
            Type("Poison") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Dragon") to DamageMultiplierCategory.NORMAL,
            Type("Dark") to DamageMultiplierCategory.NORMAL
        )
    ),
    TypeEffectiveness(
        type = SelectableType(type = Type("Fairy")),
        typesToMultipliers = mapOf(
            Type("Fighting") to DamageMultiplierCategory.DOUBLE,
            Type("Dragon") to DamageMultiplierCategory.DOUBLE,
            Type("Dark") to DamageMultiplierCategory.DOUBLE,
            Type("Fire") to DamageMultiplierCategory.HALF,
            Type("Poison") to DamageMultiplierCategory.HALF,
            Type("Steel") to DamageMultiplierCategory.HALF,
            Type("Normal") to DamageMultiplierCategory.NORMAL,
            Type("Water") to DamageMultiplierCategory.NORMAL,
            Type("Electric") to DamageMultiplierCategory.NORMAL,
            Type("Grass") to DamageMultiplierCategory.NORMAL,
            Type("Ice") to DamageMultiplierCategory.NORMAL,
            Type("Ground") to DamageMultiplierCategory.NORMAL,
            Type("Flying") to DamageMultiplierCategory.NORMAL,
            Type("Psychic") to DamageMultiplierCategory.NORMAL,
            Type("Bug") to DamageMultiplierCategory.NORMAL,
            Type("Rock") to DamageMultiplierCategory.NORMAL,
            Type("Ghost") to DamageMultiplierCategory.NORMAL,
            Type("Fairy") to DamageMultiplierCategory.NORMAL
        )
    )
)

val mockTypesWithMultipliers = listOf(
    TypesWithMultiplier(
        damageCategoryMultiplier = DamageMultiplierCategory.DOUBLE,
        typeToMultiplier = mapOf(
            Type("Fighting") to 2.0,
            Type("Ground") to 4.0,
            Type("Fire") to 2.0
        )
    ),
    TypesWithMultiplier(
        damageCategoryMultiplier = DamageMultiplierCategory.HALF,
        typeToMultiplier = mapOf(
            Type("Normal") to 0.5,
            Type("Flying") to 0.25,
            Type("Rock") to 0.5,
            Type("Bug") to 0.5,
            Type("Steel") to 0.25,
            Type("Grass") to 0.5,
            Type("Electric") to 0.5,
            Type("Psychic") to 0.5,
            Type("Ice") to 0.5,
            Type("Dragon") to 0.5,
            Type("Fairy") to 0.5
        )
    ),
    TypesWithMultiplier(
        damageCategoryMultiplier = DamageMultiplierCategory.NO_DAMAGE,
        typeToMultiplier = mapOf(
            Type("Poison") to 0.0
        )
    ),
    TypesWithMultiplier(
        damageCategoryMultiplier = DamageMultiplierCategory.NORMAL,
        typeToMultiplier = mapOf(
            Type("Ghost") to 1.0,
            Type("Water") to 1.0,
            Type("Dark") to 1.0,
        )
    )
)