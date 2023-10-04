package com.arix.pokedex.features.items.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.arix.pokedex.features.common.search_view.domain.Page
import com.arix.pokedex.features.common.search_view.domain.SearchParams
import com.arix.pokedex.features.common.search_view.ui.SearchableLazyColumn
import com.arix.pokedex.features.items.domain.model.Item
import com.arix.pokedex.features.items.presentation.ItemsViewModel
import com.arix.pokedex.features.items.presentation.ui.components.ItemBottomSheetContent
import com.arix.pokedex.features.items.presentation.ui.components.ItemListItem
import com.arix.pokedex.theme.BlackSoft
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes
import com.arix.pokedex.utils.ApiResponse
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel = getViewModel()
) {
    ItemsScreenContent { offset, searchQuery -> viewModel.getItems(offset, searchQuery) }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ItemsScreenContent(
    getItems: suspend (offset: Int, searchQuery: String) -> ApiResponse<Page<Item>>,
) {
    var clickedItem: Item by remember { mutableStateOf(Item.EMPTY) }
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
    )
    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = Shapes.bottomSheet,
        sheetContent = { ItemBottomSheetContent(clickedItem) },
        sheetBackgroundColor = BlackSoft,
        scrimColor = MaterialTheme.colors.surface.copy(0.52f)
    ) {
        SearchableLazyColumn(
            searchParams = SearchParams(
                getItems
            ), searchableContent = { items ->
                items(items.size) {
                    ItemListItem(item = items[it], onClick = { item ->
                        clickedItem = item
                        coroutineScope.launch { modalSheetState.show() }
                    })
                }
            })
    }
}

@Preview
@Composable
private fun ItemsScreenPreview() {
    PokedexTheme {
        Surface {
            Text(text = "All previews for this view are in the SearchableLazyColumnPreviews.kt")
        }
    }
}