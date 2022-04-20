package com.arix.pokedex.features.poke_list.presentation.pokemon_list.components

import android.widget.NumberPicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.theme.PokedexTheme
import org.koin.ext.clearQuotes

@Composable
fun SearchBar(onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    val isPreview = LocalInspectionMode.current
    var isFilled by remember { mutableStateOf(isPreview) }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = text,
        singleLine = true,
        label = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = null) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions { focusManager.clearFocus() },
        onValueChange = {
            text = it
            isFilled = it.isNotBlank()
            onValueChange(it)
        },
        trailingIcon = {
            if (isFilled)
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = stringResource(R.string.search_clear),
                    modifier = Modifier.clickable { text = ""; onValueChange(text) }
                )
        },
        modifier = modifier.fillMaxWidth().height(60.dp)
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