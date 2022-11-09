package com.arix.pokedex.extensions

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import com.arix.pokedex.utils.MultipleEventsCutter

/**
 * Function that extends [Modifier.clickable]
 *
 * It prevents multiple (spam) clicks.
 *
 * @param delay after this time (in millis) [onClick] function can be invoked again.
 * @see Modifier.clickable
**/
fun Modifier.clickableOnceInTime(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    delay: Long = 300L,
    onClick: () -> Unit
) = composed {
    val multipleEventsCutter = remember { MultipleEventsCutter() }
    Modifier.clickable(enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = {
            multipleEventsCutter.processEvent(delay) { onClick() }
        },
        role = role,
        indication = LocalIndication.current,
        interactionSource = remember { MutableInteractionSource() })
}