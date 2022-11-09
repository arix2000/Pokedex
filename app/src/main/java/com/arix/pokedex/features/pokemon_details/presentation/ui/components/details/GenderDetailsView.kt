package com.arix.pokedex.features.pokemon_details.presentation.ui.components.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.core.Constants
import com.arix.pokedex.extensions.formatToUserFriendlyString
import com.arix.pokedex.extensions.getPokemonGenderRatio
import com.arix.pokedex.features.common.GenderRatioGraph
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.Gender

@Composable
fun GenderDetailsView(genderRate: Int) {
    if (genderRate == -1)
        Text(stringResource(R.string.pokemon_is_genderless))
    else
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(stringResource(id = R.string.gender_ratio_label))
            GenderRatioGraph(
                genderRate.getPokemonGenderRatio(),
                modifier = Modifier.padding(vertical = 5.dp)
            )
            GenderFooter(genderRate)
        }
}

@Composable
private fun GenderFooter(genderRate: Int) {
    when (genderRate) {
        Constants.PokemonGenderConst.GANDER_RATE_WHEN_ONLY_MALES -> Text(stringResource(R.string.pokemon_is_male_only))
        Constants.PokemonGenderConst.GANDER_RATE_WHEN_ONLY_FEMALES -> Text(stringResource(R.string.pokemon_is_female_only))
        else -> Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(
                    R.string.gender_female_label,
                    calculateGenderRateBy(Gender.Female, genderRate)
                )
            )
            Text(
                stringResource(
                    R.string.gender_male_label,
                    calculateGenderRateBy(Gender.Male, genderRate)
                )
            )
        }
    }
}

private fun calculateGenderRateBy(gender: Gender, genderRatioRaw: Int): String {
    return when (gender) {
        Gender.Male ->
            ((genderRatioRaw.getPokemonGenderRatio() - 1).unaryMinus() * 100)
                .formatToUserFriendlyString()
        Gender.Female ->
            (genderRatioRaw.getPokemonGenderRatio() * 100).formatToUserFriendlyString()
    }
}

@Preview
@Composable
private fun GenderDetailsViewPreview() {
    PokedexTheme {
        Surface {
            GenderDetailsView(3)
        }
    }
}