package com.arix.pokedex.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arix.pokedex.R
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.TextSize

@Composable
fun ErrorScreenWithRetryButton(
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit,
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.default_error_message),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(onClick = onRetryClicked) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun ErrorScreenWithRetryButtonCondensed(
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit,
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.condensed_error_message),
                textAlign = TextAlign.Center,
                fontSize = TextSize.xsmall
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = onRetryClicked,
                modifier = Modifier.height(35.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = stringResource(R.string.retry), fontSize = TextSize.xsmall)
            }
        }
    }
}

@Preview
@Composable
fun ErrorScreenWithRetryButtonPreview() {
    PokedexTheme {
        Surface {
            ErrorScreenWithRetryButton(modifier = Modifier.fillMaxWidth()) { }
        }
    }
}

@Preview
@Composable
fun ErrorScreenWithRetryButtonCondensedPreview() {
    PokedexTheme {
        Surface {
            ErrorScreenWithRetryButtonCondensed(modifier = Modifier.fillMaxWidth()) { }
        }
    }
}