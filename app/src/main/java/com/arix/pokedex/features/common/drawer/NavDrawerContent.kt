package com.arix.pokedex.features.common.drawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.arix.pokedex.theme.WhiteA50
import com.arix.pokedex.views.FadingHorizontalDivider
import kotlinx.coroutines.launch

@Composable
fun NavDrawerContent(navController: NavController, scaffoldState: ScaffoldState) {
    val uriHandler = LocalUriHandler.current
    val drawerSpecsList = remember { DrawerScreens.entries.toTypedArray() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scope = rememberCoroutineScope()

    BackHandler(scaffoldState.drawerState.isOpen) {
        scope.launch { scaffoldState.drawerState.close() }
    }

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
        Spacer(modifier = Modifier.weight(1F))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.powered_by_poke_api_label), color = WhiteA50)
            Spacer(Modifier.height(8.dp))
            Image(
                painter = painterResource(R.drawable.pokeapi_logo),
                contentDescription = "PokeapiLink",
                modifier = Modifier
                    .height(60.dp)
                    .clickable {
                        uriHandler.openUri("https://pokeapi.co/")
                    }
            )
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
        Text(text = drawerSpecs.title, color = Color.LightGray)
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