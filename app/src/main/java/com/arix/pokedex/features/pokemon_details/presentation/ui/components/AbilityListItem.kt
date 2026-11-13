package com.arix.pokedex.features.pokemon_details.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arix.pokedex.R
import com.arix.pokedex.features.pokemon_list.domain.model.details.Ability
import com.arix.pokedex.theme.BlackLighter
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.utils.MockResourceReader

@Composable
fun AbilityListItem(ability: Ability) {
    Column(
        Modifier
            .background(color = BlackLighter, shape = Shapes.large)
            .padding(vertical = 12.dp)
            .clip(shape = Shapes.large)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 15.dp, end = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = ability.name.capitalize(LocaleList.current).replace("-", " "),
                    fontSize = 18.sp
                )
                if (ability.isHidden) {
                    Spacer(modifier = Modifier.width(5.dp))
                    HiddenLabel()
                }
            }
        }
        Text(
            text = ability.description,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
    }
}

@Composable
fun HiddenLabel() {
    Surface(shape = Shapes.large) {
        Text(
            text = stringResource(R.string.hidden_label),
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
            fontSize = FontSizes.minimum,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun AbilityListItemPreview() {
    PokedexTheme {
        val context = LocalContext.current
        val pokemonDetails = remember {
            MockResourceReader(context).getPokemonDetailsMock()
        }
        Surface(modifier = Modifier.fillMaxWidth()) {
            AbilityListItem(pokemonDetails.abilities.last())
        }
    }
}