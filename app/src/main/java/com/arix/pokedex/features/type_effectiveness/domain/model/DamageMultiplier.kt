package com.arix.pokedex.features.type_effectiveness.domain.model

import androidx.annotation.StringRes
import com.arix.pokedex.R

enum class DamageMultiplierCategory(
    val baseMultiplier: Double, val multiplierRange: ClosedRange<Double>
) {
    DOUBLE(baseMultiplier = 2.0, multiplierRange = 2.0..4.0),
    HALF(baseMultiplier = 0.5, multiplierRange = 0.25..0.5),
    NO_DAMAGE(baseMultiplier = 0.0, multiplierRange = 0.0..0.0),
    NORMAL(baseMultiplier = 1.0, multiplierRange = 1.0..1.0);

    @StringRes
    fun getTitleResId(): Int {
        return when (this) {
            DOUBLE -> R.string.damage_multiplier_double_label
            HALF -> R.string.damage_multiplier_half_label
            NO_DAMAGE -> R.string.damage_multiplier_no_damage_label
            NORMAL -> R.string.damage_multiplier_normal_label
        }
    }
}