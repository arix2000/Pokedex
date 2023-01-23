package com.arix.pokedex.features.pokemon_list.presentation.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Primary

@Composable
fun SearchBar(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    clearOnBackPress: Boolean = true
) {
    var text by rememberSaveable { mutableStateOf("") }
    val isPreview = LocalInspectionMode.current
    var isFilled by rememberSaveable { mutableStateOf(isPreview) }
    val focusManager = LocalFocusManager.current
    val unfocusedColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
    var iconsColor: Color by remember { mutableStateOf(unfocusedColor) }

    BackHandler(text.isNotEmpty() && clearOnBackPress) {
        text = ""
        onValueChange(text)
    }

    OutlinedTextField(
        value = text,
        singleLine = true,
        shape = CircleShape,
        label = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = iconsColor
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions { focusManager.clearFocus() },
        onValueChange = {
            text = it
            isFilled = it.isNotBlank()
            onValueChange(it)
        },
        trailingIcon = {
            if (isFilled)
                IconButton(
                    onClick = {
                        text = ""
                        onValueChange(text)
                        isFilled = false
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = stringResource(R.string.search_clear),
                        tint = iconsColor
                    )
                }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .onFocusChanged {
                iconsColor = if (it.isFocused)
                    Primary
                else unfocusedColor
            }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    PokedexTheme {
        Surface {
            SearchBar({})
        }
    }
}