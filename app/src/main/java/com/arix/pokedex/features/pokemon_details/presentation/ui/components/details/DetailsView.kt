package com.arix.pokedex.features.pokemon_details.presentation.ui.components.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arix.pokedex.R
import com.arix.pokedex.features.poke_list.domain.model.PokemonDetailTag
import com.arix.pokedex.features.poke_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.FadingHorizontalDivider

@Composable
fun DetailsView(pokemonDetails: PokemonDetails, species: PokemonSpecies) {
    val pokemonDetailTag = remember { PokemonDetailTag.fromSpecies(species) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        IdRow(pokemonDetails.id)
        Spacer(Modifier.height(10.dp))
        GenerationAndHabitat(species)
        FadingHorizontalDivider(Modifier.padding(vertical = 5.dp))
        CatchAndHappinessInfo(species)
        FadingHorizontalDivider(Modifier.padding(vertical = 5.dp))
        PhysicalInfoRow(pokemonDetails)
        FadingHorizontalDivider(Modifier.padding(vertical = 5.dp))
        GenderDetailsView(species.gender_rate)
        Spacer(Modifier.height(10.dp))
        if (pokemonDetailTag.notNone())
            PokemonTagView(pokemonDetailTag)
    }
}

@Composable
private fun IdRow(pokemonId: Int) {
    Text(
        text = stringResource(R.string.id_label, pokemonId),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}

@Composable
private fun GenerationAndHabitat(species: PokemonSpecies) {
    InheritedRow(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = species.getGenerationName())
        Text(
            text = stringResource(
                R.string.habitat_label,
                species.habitat?.name?.replace("-", " ")
                    ?: stringResource(R.string.none)
            )
        )
    }
}

@Composable
private fun InheritedRow(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = verticalAlignment,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        content = content
    )
}

@Composable
private fun CatchAndHappinessInfo(species: PokemonSpecies) {
    InheritedRow {
        Text(text = stringResource(R.string.catch_rate_label, species.capture_rate))
        Text(text = stringResource(R.string.base_happiness_label, species.base_happiness))
    }
}

@Composable
private fun PhysicalInfoRow(pokemonDetails: PokemonDetails) {
    InheritedRow {
        with(pokemonDetails) {
            Text(text = stringResource(R.string.height_label, getHeightInMeters()))
            Text(
                text = stringResource(
                    R.string.weight_label,
                    getWeightInKilograms()
                )
            )
        }
    }
}

@Composable
private fun PokemonTagView(pokemonDetailTag: PokemonDetailTag) {
    Box(
        modifier = Modifier
            .background(pokemonDetailTag.color, CircleShape)
    ) {
        Text(
            text = pokemonDetailTag.getName(),
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
        )
    }
}

@Preview
@Composable
private fun DetailsSectionPreview() {
    PokedexTheme {
        val context = LocalContext.current
        val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
        val pokemonSpecies = remember { MockResourceReader(context).getPokemonSpeciesMock() }
        Surface {
            DetailsView(pokemonDetails, pokemonSpecies)
        }
    }
}