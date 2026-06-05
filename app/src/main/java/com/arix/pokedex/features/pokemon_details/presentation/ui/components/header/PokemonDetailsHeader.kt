package com.arix.pokedex.features.pokemon_details.presentation.ui.components.header

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.extensions.isPreview
import com.arix.pokedex.features.pokemon_details.domain.model.species.PokemonSpecies
import com.arix.pokedex.features.pokemon_list.domain.model.details.PokemonDetails
import com.arix.pokedex.features.pokemon_list.presentation.ui.components.TypesSection
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.theme.WhiteA50
import com.arix.pokedex.utils.MockResourceReader
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.FadingHorizontalDivider

@Composable
fun PokemonDetailsHeader(
    pokemonDetails: PokemonDetails,
    species: PokemonSpecies,
    onImageClicked: (url: String) -> Unit
) {
    var imageModel by remember {
        mutableStateOf(pokemonDetails.sprites.front_default)
    }
    var isImageLoading by remember { mutableStateOf(true) }

    val mediaPlayer = if (!isPreview()) remember { MediaPlayer() } else null

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    fun playCrySound() {
        try {
            mediaPlayer?.reset()
            mediaPlayer?.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            mediaPlayer?.setDataSource(pokemonDetails.cryUrl)
            mediaPlayer?.setOnPreparedListener { mp ->
                mp.start()
            }
            mediaPlayer?.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Box {
        BackgroundGradientBasedOn(pokemonDetails.types)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Box(Modifier.height(250.dp)) {
                if (isImageLoading)
                    DefaultProgressIndicatorScreen(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                AsyncImage(
                    model = imageModel,
                    contentDescription = pokemonDetails.name,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .align(Alignment.Center)
                        .clickable(
                            indication = rememberRipple(bounded = false, radius = 130.dp),
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onImageClicked(imageModel) },
                    error = painterResource(id = R.drawable.pokemon_not_found_image),
                    placeholder = if (isPreview()) painterResource(id = R.drawable.scyther) else null,
                    onLoading = { isImageLoading = true },
                    onSuccess = { isImageLoading = false },
                    onError = { isImageLoading = false }
                )
            }
            FadingHorizontalDivider(modifier = Modifier.padding(top = 12.dp, bottom = 8.dp))
            Text(
                text = pokemonDetails.name.capitalize(LocaleList.current),
                fontSize = FontSizes.extraLarge
            )
            Text(text = species.getGeneraText(), color = WhiteA50)
            TypesSection(
                types = pokemonDetails.types,
                spacing = 8.dp,
                itemFontSize = FontSizes.medium,
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .width(90.dp)
            )
        }
        if (pokemonDetails.sprites.front_shiny != null)
            ShinyToggleButton(imageModel, pokemonDetails, onClick = { imageModel = it })
        if (pokemonDetails.cryUrl.isNotEmpty())
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable {
                        playCrySound()
                    }
                    .background(
                        (pokemonDetails.types.getOrNull(1)
                            ?: pokemonDetails.types.first()).getTypeColor(),
                        shape = CircleShape
                    )
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.volume_up),
                    contentDescription = "Cry",
                    modifier = Modifier.size(20.dp)
                )
            }
    }
}

@Composable
fun ShinyToggleButton(
    imageModel: String,
    pokemonDetails: PokemonDetails,
    onClick: (String) -> Unit
) {
    val isShinyShowed = imageModel == pokemonDetails.sprites.front_shiny
    Button(
        contentPadding = PaddingValues(
            top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp
        ),
        shape = Shapes.large,
        modifier = Modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .padding(start = 9.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = pokemonDetails.types.first().getTypeColor()
        ),
        onClick = {
            onClick(
                if (isShinyShowed)
                    pokemonDetails.sprites.front_default
                else pokemonDetails.sprites.front_shiny ?: ""
            )
        }) {
        Text(text = if (isShinyShowed) "Normal" else "Shiny")
    }
}

@Preview
@Composable
fun PokemonDetailsHeaderPreview() {
    val context = LocalContext.current
    val pokemonDetails = remember { MockResourceReader(context).getPokemonDetailsMock() }
    val species = remember { MockResourceReader(context).getPokemonSpeciesMock() }
    PokedexTheme {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                PokemonDetailsHeader(pokemonDetails, species) {}
            }
        }
    }
}