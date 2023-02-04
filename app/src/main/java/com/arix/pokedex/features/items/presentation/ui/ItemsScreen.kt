package com.arix.pokedex.features.items.presentation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.items.presentation.ItemsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel = getViewModel()
) {
    val state = viewModel.state.value
    Text(text = state.itemNames.joinToString())
}

@Preview
@Composable
private fun ItemsScreenPreview() {

}