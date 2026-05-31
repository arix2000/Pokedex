package com.arix.pokedex.features.type_effectiveness.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.arix.pokedex.extensions.toFraction
import com.arix.pokedex.features.pokemon_list.domain.model.details.Type
import com.arix.pokedex.theme.BlackSoft
import com.arix.pokedex.theme.PokedexTheme

@Composable
fun TypeWithMultiplierItem(
    typeWithMultiplier: Pair<Type, Double>,
    modifier: Modifier = Modifier
) {
    val type: Type = typeWithMultiplier.first
    val multiplier: Double = typeWithMultiplier.second
    Box(
        modifier = modifier
            .background(type.getTypeColor(), CircleShape)
            .padding(top = 2.dp, bottom = 2.dp, start = 2.dp, end = 26.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                MultiplierText(multiplier)
            }
            Spacer(Modifier.width(8.dp))
            Text(text = type.name, softWrap = false, lineHeight = 0.6.em)
        }
    }

}

@Composable
fun MultiplierText(multiplier: Double) {
    if (multiplier < 1 && multiplier > 0)
        FractionText(multiplier)
    else
        Text(
            modifier = Modifier.padding(2.dp),
            text = multiplier.toInt().toString() + "x",
            color = BlackSoft,
            fontSize = 14.sp,
        )
}

@Composable
private fun FractionText(multiplier: Double) {
    val noFontPaddingTextStyle = TextStyle(
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
    )

    val fraction = multiplier.toFraction()
    val numerator = fraction.first
    val dominator = fraction.second

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = numerator.toString(),
                color = BlackSoft,
                fontSize = 10.sp,
                style = noFontPaddingTextStyle
            )
            Divider(
                thickness = 0.5.dp,
                color = BlackSoft,
                modifier = Modifier.width(7.dp)
            )
            Text(
                text = dominator.toString(),
                color = BlackSoft,
                fontSize = 10.sp,
                style = noFontPaddingTextStyle
            )
        }
        Text(
            "x", color = BlackSoft,
            fontSize = 14.sp,
        )
    }
}

@Preview
@Composable
private fun SelectableTypeItemPreview() {
    PokedexTheme {
        Surface {
            Column(Modifier.padding(16.dp)) {
                TypeWithMultiplierItem(
                    typeWithMultiplier = Pair(Type("ground"), 0.5)
                )
                Spacer(Modifier.height(12.dp))
                TypeWithMultiplierItem(
                    typeWithMultiplier = Pair(Type("ground"), 2.0)
                )
            }
        }
    }
}