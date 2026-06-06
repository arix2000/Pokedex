package com.arix.pokedex.features.common.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.arix.pokedex.R
import com.arix.pokedex.theme.BlackLight
import com.arix.pokedex.theme.LightGray
import com.arix.pokedex.theme.PokedexTheme
import com.arix.pokedex.theme.Shapes

@Composable
fun AppIconSelectorDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = Shapes.large,
            color = BlackLight,
            elevation = 8.dp
        ) {
            ProvideTextStyle(TextStyle(color = LightGray)) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.app_icon_change_warning_title),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = stringResource(R.string.app_icon_change_warning_text))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = onConfirm,
                            shape = CircleShape
                        ) {
                            Text("Proceed")
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = LightGray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AppIconSelectorDialogPreview() {
    PokedexTheme {
        Surface {
            AppIconSelectorDialog(onDismiss = {}) { }
        }
    }
}