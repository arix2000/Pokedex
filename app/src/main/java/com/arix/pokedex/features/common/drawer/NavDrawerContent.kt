package com.arix.pokedex.features.common.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arix.pokedex.R
import com.arix.pokedex.core.navigation.DrawerScreens
import com.arix.pokedex.core.navigation.DrawerSpecs
import com.arix.pokedex.theme.BlackLight
import com.arix.pokedex.theme.BlackSoftA50
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.views.FadingHorizontalDivider
import kotlinx.coroutines.launch

@Composable
fun NavDrawerContent(navController: NavController, scaffoldState: ScaffoldState) {
    val drawerSpecsList = remember { DrawerScreens.values() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scope = rememberCoroutineScope()

    Spacer(modifier = Modifier.height(20.dp))
    Image(
        painter = painterResource(id = R.drawable.pokemon_logo),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    )
    FadingHorizontalDivider(modifier = Modifier.padding(vertical = 20.dp))
    Column {
        drawerSpecsList.forEach {
            NavDrawerListItem(
                it.drawerSpecs,
                selected = currentRoute == it.screen.route
            ) {
                navController.navigate(it.screen.route) {
                    popUpTo(0)
                }
                scope.launch { scaffoldState.drawerState.close() }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
private fun NavDrawerListItem(
    drawerSpecs: DrawerSpecs,
    selected: Boolean,
    onClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(
                if (selected) BlackSoftA50 else Color.Transparent,
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .clickable { onClicked() }
            .padding(16.dp)
    ) {
        Image(painter = painterResource(id = drawerSpecs.iconId), contentDescription = null)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = drawerSpecs.title)
    }
}

@Preview
@Composable
private fun NavDrawerContentPreview() {
    PokedexTheme {
        Surface(modifier = Modifier.width(360.dp), color = BlackLight, contentColor = Color.White) {
            Column {
                NavDrawerContent(rememberNavController(), rememberScaffoldState())
            }
        }
    }
}