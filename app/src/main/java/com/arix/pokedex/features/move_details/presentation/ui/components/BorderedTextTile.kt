package com.arix.pokedex.features.move_details.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.arix.pokedex.features.common.text.ExpandableText
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.theme.*

@Composable
fun BorderedTextTile(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    borderColor: Color,
    enabled: Boolean = true
) {
    Box(
        modifier
            .border(
                0.5.dp,
                Gradients.getBorderVerticalGradient(if (enabled) borderColor else DisabledColor),
                Shapes.large
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = label,
                color = if (enabled) Color.Unspecified else DisabledColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                color = if (enabled) Color.Unspecified else DisabledColor
            )
        }
    }
}

@Composable
fun BorderedTextTile(
    modifier: Modifier = Modifier,
    title: String? = null,
    text: String,
    borderColor: Color,
    contentPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 15.dp)
) {
    Column(
        modifier
            .border(0.5.dp, Gradients.getBorderVerticalGradient(borderColor), Shapes.large)
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (title != null)
            Text(text = title, fontSize = FontSizes.large, textAlign = TextAlign.Center)
        ExpandableText(text = text)
    }
}


@Composable
fun BorderedTextTile(
    modifier: Modifier = Modifier,
    borderColor: Color,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier
            .border(
                0.5.dp,
                Gradients.getBorderVerticalGradient(if (enabled) borderColor else DisabledColor),
                Shapes.large
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            content()
        }
    }
}

@Composable
fun BorderedTextTile(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    borderColor: Color,
    contentPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 15.dp)
) {
    Column(
        modifier
            .border(0.5.dp, Gradients.getBorderVerticalGradient(borderColor), Shapes.large)
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ExpandableText(text = text)
    }
}

@Preview
@Composable
private fun BorderedTextTilePreview() {
    PokedexTheme {
        Surface {
            Row(modifier = Modifier.padding(10.dp)) {
                BorderedTextTile(
                    Modifier
                        .width(150.dp)
                        .height(80.dp),
                    "Some label:",
                    "100%",
                    Type("grass").getTypeColor(),
                )
                Spacer(Modifier.width(10.dp))
                BorderedTextTile(
                    Modifier
                        .width(150.dp)
                        .height(80.dp),
                    "Some label:",
                    "100%",
                    Type("grass").getTypeColor(),
                    false
                )
            }
        }
    }
}

@Preview
@Composable
private fun BorderedTextTileOnlyValuePreview() {
    PokedexTheme {
        Surface {
            Column(modifier = Modifier.padding(10.dp)) {
                BorderedTextTile(
                    Modifier.fillMaxWidth(),
                    null,
                    LoremIpsum(20).values.joinToString(" "),
                    Type("grass").getTypeColor(),
                    PaddingValues(vertical = 10.dp, horizontal = 15.dp)
                )

                BorderedTextTile(
                    Modifier.fillMaxWidth(),
                    "Some not so long title",
                    LoremIpsum(20).values.joinToString(" "),
                    Type("grass").getTypeColor(),
                    PaddingValues(vertical = 10.dp, horizontal = 15.dp)
                )

                BorderedTextTile(
                    Modifier.fillMaxWidth(),
                    buildAnnotatedString {
                        append(LoremIpsum(10).values.joinToString(" "))
                        append(
                            AnnotatedString(
                                " some text with other style ",
                                spanStyle = SpanStyle(background = Color.Blue)
                            )
                        )
                        append(LoremIpsum(10).values.joinToString(" "))
                    },
                    Type("grass").getTypeColor(),
                    PaddingValues(vertical = 10.dp, horizontal = 15.dp)
                )
            }
        }
    }
}