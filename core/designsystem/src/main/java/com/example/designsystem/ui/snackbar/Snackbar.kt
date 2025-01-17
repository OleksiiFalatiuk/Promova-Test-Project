package com.example.designsystem.ui.snackbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.designsystem.resources.dimens.Dimens
import com.example.core.localization.R as LocalizableResources

@Composable
fun Snackbar(snackbarData: SnackbarData, onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    androidx.compose.material3.Snackbar(
        modifier = modifier
            .padding(
                start = Dimens.spacingNormal,
                end = Dimens.spacingNormal,
                bottom = Dimens.spacingNormal
            ),
        containerColor = Color.White,
        action = {
            Text(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onDismiss
                    )
                    .padding(
                        end = Dimens.spacingNormal
                    ),
                text = stringResource(id = LocalizableResources.string.snackbar_title),
                color = Color.Black,
                style = LocalTextStyle.current
            )
        },
        content = {
            Column(modifier = Modifier) {
                Text(
                    text = stringResource(id = LocalizableResources.string.snackbar_title),
                    color = Color.Black,
                    style = LocalTextStyle.current
                )

                Text(
                    text = stringResource(LocalizableResources.string.snackbar_message).format(
                        snackbarData.visuals.message
                    ),
                    color = Color.Black,
                    style = LocalTextStyle.current
                )
            }
        }
    )
}