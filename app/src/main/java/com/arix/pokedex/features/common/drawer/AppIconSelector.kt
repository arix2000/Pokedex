package com.arix.pokedex.features.common.drawer

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.arix.pokedex.core.base.AppIcons
import com.arix.pokedex.features.common.boxes.OffsetCheckIndicator
import com.arix.pokedex.theme.BlackLighterAlternative
import com.arix.pokedex.theme.LightGray
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes

@Composable
fun AppIconSelector() {
    val context = LocalContext.current
    var currentIconAlias by remember { mutableStateOf(getCurrentActiveIcon(context)) }
    var showWarningDialog by remember { mutableStateOf(false) }
    var pendingIconAlias by remember { mutableStateOf<String?>(null) }

    if (showWarningDialog && pendingIconAlias != null) {
        AppIconSelectorDialog(
            onConfirm = {
                changeAppIcon(context, pendingIconAlias!!)
                currentIconAlias = pendingIconAlias!!
                showWarningDialog = false

            },
            onDismiss = {
                showWarningDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            for (icon in AppIcons.entries) {
                Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.Center) {
                    Box {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(BlackLighterAlternative, shape = Shapes.medium)
                                .border(
                                    2.dp,
                                    color = if (currentIconAlias == icon.alias) LightGray else Color.Transparent,
                                    shape = Shapes.medium
                                )
                                .padding(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = icon.iconId),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(44.dp)
                                    .clickable {
                                        if (currentIconAlias != icon.alias) {
                                            pendingIconAlias = icon.alias
                                            showWarningDialog = true
                                        }
                                    }
                            )
                        }
                        if (currentIconAlias == icon.alias)
                            OffsetCheckIndicator(
                                2.dp,
                                LightGray,
                                offset = DpOffset(6.dp, 4.dp)
                            )
                    }
                }
            }
        }
    }
}

fun changeAppIcon(context: Context, newAlias: String) {
    val packageManager = context.packageManager
    val packageName = context.packageName

    AppIcons.entries.forEach { iconData ->
        val state = if (iconData.alias == newAlias) {
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        } else {
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        }

        packageManager.setComponentEnabledSetting(
            ComponentName(packageName, "$packageName.${iconData.alias}"),
            state,
            PackageManager.DONT_KILL_APP
        )
    }

    Runtime.getRuntime().exit(0)
}

fun getCurrentActiveIcon(context: Context): String {
    val packageManager = context.packageManager
    val packageName = context.packageName

    for (componentName in AppIcons.entries.map { it.alias }) {
        val component = ComponentName(packageName, "$packageName.$componentName")
        val state = packageManager.getComponentEnabledSetting(component)

        if (state == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            return componentName
        }
    }
    return AppIcons.DEFAULT.alias
}

@Preview
@Composable
private fun AppIconSelectorPreview() {
    PokedexTheme {
        Surface {
            Box {
                AppIconSelector()
            }
        }
    }
}