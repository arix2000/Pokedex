package com.arix.pokedex.features.common

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.R
import com.arix.pokedex.features.common.text.SlidingText
import com.arix.pokedex.theme.BlackSoft
import com.arix.pokedex.theme.PokedexTheme
import kotlinx.coroutines.launch

@Composable
fun AppTopBar(
    navController: NavController,
    showBackButton: Boolean,
    scaffoldState: ScaffoldState,
    title: String? = null
) {
    TopAppBar(backgroundColor = BlackSoft, contentPadding = PaddingValues(all = 0.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (title == null) {
                Image(
                    painter = painterResource(id = R.drawable.pokemon_logo),
                    contentDescription = "Pokemon Logo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(all = 4.dp)
                        .align(Alignment.Center)
                )
                HomeIconButton(navController, modifier = Modifier.align(Alignment.CenterEnd))
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 4.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showBackButton)
                    BackIconButton(navController)
                else
                    MenuIconButton(scaffoldState)

                if (title != null) {
                    SlidingText(
                        text = title,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                            letterSpacing = 0.15.sp
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp, end = 15.dp)
                    )
                    HomeIconButton(navController)
                }
            }
        }
    }
}

@Composable
private fun BackIconButton(navController: NavController) {
    IconButton(onClick = { navController.navigateUp() }) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "Back"
        )
    }
}

@Composable
private fun MenuIconButton(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    IconButton(
        onClick = {
            scope.launch { scaffoldState.drawerState.open() }
        },
    ) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = "Menu"
        )
    }
}

@SuppressLint("RestrictedApi")
@Composable
private fun HomeIconButton(navController: NavController, modifier: Modifier = Modifier) {
    val backStack by navController.currentBackStack.collectAsState()
    val oldestRouteInStack = backStack
        .mapNotNull { it.destination.route }
        .firstOrNull { it != navController.graph.route }
    if (oldestRouteInStack != null && backStack.lastOrNull()?.destination?.route != oldestRouteInStack)
        IconButton(
            modifier = modifier,
            onClick = {
                navController.popBackStack(
                    route = oldestRouteInStack,
                    inclusive = false
                )
            },
        ) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "Home"
            )
        }
}

@Preview
@Composable
private fun AppTopBarPreview() {
    PokedexTheme {
        Surface {
            AppTopBar(rememberNavController(), false, rememberScaffoldState())
        }
    }
}

@Preview
@Composable
private fun AppTopBarTitlePreview() {
    PokedexTheme {
        Surface {
            AppTopBar(
                rememberNavController(),
                true,
                rememberScaffoldState(),
                "Tackle can be learned by:"
            )
        }
    }
}