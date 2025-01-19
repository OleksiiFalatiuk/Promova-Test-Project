package com.promovatestproject.features.home.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicBlack
import com.example.designsystem.resources.figma.typography.FigmaTypography
import com.example.core.localization.R as LocalizableResources

@Composable
internal fun FilmsEmptyContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = LocalizableResources.string.no_data_title),
            color = FigmaColors.basicBlack(),
            style = FigmaTypography.bodyLarge
        )
    }
}