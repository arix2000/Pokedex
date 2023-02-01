package com.arix.pokedex.features.pokemon_details.presentation.ui.components.full_screen_image_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.arix.pokedex.R
import com.arix.pokedex.features.common.boxes.ZoomableBox
import com.arix.pokedex.theme.DefaultBackgroundColorA90
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun FullScreenImageDialog(isDialogShowed: MutableState<Boolean>, imageUrl: String) {
    if (isDialogShowed.value)
        Dialog(
            onDismissRequest = { isDialogShowed.value = false }) {
            ZoomableBox(
                modifier = Modifier
                    .requiredWidth(LocalConfiguration.current.screenWidthDp.dp)
                    .fillMaxHeight()
                    .background(color = DefaultBackgroundColorA90)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Pokemon Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .align(Alignment.Center)
                        .graphicsLayer(
                            scaleX = it.scale,
                            scaleY = it.scale,
                            translationX = it.offsetX,
                            translationY = it.offsetY
                        ),
                    placeholder = painterResource(id = R.drawable.scyther),
                    error = painterResource(id = R.drawable.pokemon_not_found_image),
                )
                FullScreenDialogTopBar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    onCancelClicked = { isDialogShowed.value = false })
            }
        }
}

@Preview
@Composable
private fun ImageFullScreenDialogPreview() {
    PokedexTheme {
        Surface {
            FullScreenImageDialog(
                isDialogShowed =
                remember {
                    mutableStateOf(true)
                },
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/123.png"
            )
        }
    }
}