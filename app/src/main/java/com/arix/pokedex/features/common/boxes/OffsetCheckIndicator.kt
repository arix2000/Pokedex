package com.arix.pokedex.features.common.boxes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.SuccessColor

@Composable
fun BoxScope.OffsetCheckIndicator(
    borderWidth: Dp,
    borderColor: Color,
    offset: DpOffset = DpOffset(4.dp, 4.dp)
) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .size(20.dp)
            .offset(offset.x, offset.y)
            .background(SuccessColor, CircleShape)
            .border(borderWidth, borderColor, CircleShape)
            .padding(2.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Done,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview
@Composable
private fun OffsetCheckIndicatorPreview() {
    PokedexTheme {
        Surface {
            Box(Modifier.size(128.dp)) {
                Box(Modifier.size(64.dp)) {
                    OffsetCheckIndicator(borderWidth = 2.dp, borderColor = Color.White)
                }
            }
        }
    }
}