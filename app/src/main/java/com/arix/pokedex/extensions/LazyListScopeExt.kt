package com.arix.pokedex.extensions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun <T> LazyListScope.gridItems(
    data: List<T>,
    cells: Int,
    alwaysFitSpace: Boolean = false,
    itemContent: @Composable BoxScope.(T) -> Unit
) {
    items(data.chunked(cells)) { row ->
        if (alwaysFitSpace)
            Row {
                row.forEach { item ->
                    Box(Modifier.weight(1f)) {
                        itemContent.invoke(this, item)
                    }
                }
            }
        else
            Row(Modifier.fillMaxWidth()) {
                row.forEachIndexed { index, item ->
                    Box(Modifier.fillMaxWidth(1f / (cells - index))) {
                        itemContent.invoke(this, item)
                    }
                }
            }
    }
}
