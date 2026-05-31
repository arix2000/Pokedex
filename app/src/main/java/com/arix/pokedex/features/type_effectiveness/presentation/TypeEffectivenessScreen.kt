package com.arix.pokedex.features.type_effectiveness.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arix.pokedex.R
import com.arix.pokedex.features.type_effectiveness.domain.model.SelectableType
import com.arix.pokedex.features.type_effectiveness.presentation.ui.TypeEffectivenessEvent
import com.arix.pokedex.features.type_effectiveness.presentation.ui.TypeEffectivenessState
import com.arix.pokedex.features.type_effectiveness.presentation.ui.TypeEffectivenessViewModel
import com.arix.pokedex.features.type_effectiveness.presentation.ui.components.SelectableTypesList
import com.arix.pokedex.features.type_effectiveness.presentation.ui.components.TypesWithMultiplierSection
import com.arix.pokedex.theme.Accent
import com.arix.pokedex.theme.FontSizes
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.PrimarySemiTransparent
import com.arix.pokedex.views.DefaultProgressIndicatorScreen
import com.arix.pokedex.views.ErrorScreenWithRetryButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun TypeEffectivenessScreen(viewModel: TypeEffectivenessViewModel = getViewModel()) {
    val state = viewModel.state.value
    when {
        state.typeEffectivenessList.isNotEmpty() -> {
            TypeEffectivenessScreenContent(state, { event -> viewModel.invokeEvent(event) })
        }

        state.isLoading -> DefaultProgressIndicatorScreen()
        state.errorMessage != null -> ErrorScreenWithRetryButton {
            viewModel.invokeEvent(TypeEffectivenessEvent.GetTypesEvent())
        }
    }
}

@Composable
private fun TypeEffectivenessScreenContent(
    state: TypeEffectivenessState,
    invokeEvent: (TypeEffectivenessEvent) -> Unit
) {
    /** TODO plan:
     * make clear types button
     * and add animation to showing type effectiveness table
     * **/
    val coroutineScope = rememberCoroutineScope()

    val selectedTypesCount = state.getSelectedCount()
    var shouldHighlightSelectedTypesCounter by remember { mutableStateOf(false) }

    val handleTypeClick: (SelectableType) -> Unit = { type: SelectableType ->
        if (state.getSelectedCount() >= 2 && !type.isSelected) {
            coroutineScope.launch {
                shouldHighlightSelectedTypesCounter = true
                delay(2000)
                shouldHighlightSelectedTypesCounter = false
            }
        } else {
            invokeEvent(TypeEffectivenessEvent.SelectTypeEvent(type))
        }
    }

    Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 56.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.selected_types_label),
                    fontSize = FontSizes.large,
                    fontWeight = FontWeight.Medium
                )
                SelectedTypesCounter(shouldHighlightSelectedTypesCounter, selectedTypesCount)
            }
            SelectableTypesList(
                state.typeEffectivenessList.map { it.type },
                onTypeClick = handleTypeClick
            )
            if (state.getSelectedCount() > 0) {
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .clip(CircleShape)
                            .background(color = PrimarySemiTransparent)
                            .clickable {
                                invokeEvent(TypeEffectivenessEvent.ClearTypesEvent())
                            }
                            .padding(vertical = 4.dp, horizontal = 18.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp,
                            alignment = Alignment.End
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.clear_types_label),
                            fontSize = 18.sp,
                        )
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
            state.mergedTypeEffectiveness.forEach { typesWithMultiplier ->
                if (typesWithMultiplier.typeToMultiplier.isNotEmpty())
                    TypesWithMultiplierSection(
                        stringResource(typesWithMultiplier.damageCategoryMultiplier.getTitleResId()),
                        typesWithMultiplier.typeToMultiplier,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    )
            }
        }
    }
}

@Composable
private fun SelectedTypesCounter(
    shouldHighlightSelectedTypesCounter: Boolean,
    selectedTypesCount: Int
) {
    val rotation = remember { Animatable(0f) }
    val color by animateColorAsState(if (shouldHighlightSelectedTypesCounter) Accent else Color.White)

    LaunchedEffect(shouldHighlightSelectedTypesCounter) {
        if (shouldHighlightSelectedTypesCounter) {
            rotation.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 300
                    -8f at 50
                    8f at 100
                    -8f at 150
                    8f at 200
                    -8f at 250
                    0f at 300
                }
            )
        }
    }

    Text(
        text = stringResource(R.string.selected_types_count, selectedTypesCount),
        fontSize = FontSizes.large,
        color = color,
        modifier = Modifier.graphicsLayer {
            rotationZ = rotation.value
        }
    )
}

@Preview
@Composable
private fun TypeEffectivenessScreenPreview() {

    PokedexTheme {
        Surface {
            TypeEffectivenessScreenContent(
                TypeEffectivenessState(
                    typeEffectivenessList = mockTypeEffectivenessList,
                    mergedTypeEffectiveness = mockTypesWithMultipliers
                )
            ) { }
        }
    }
}