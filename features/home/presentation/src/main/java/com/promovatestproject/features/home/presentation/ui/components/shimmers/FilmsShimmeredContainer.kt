package com.promovatestproject.features.home.presentation.ui.components.shimmers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.components.shimmer.shimmer
import com.example.designsystem.resources.constants.Constants
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.typography.FigmaTypography
import com.promovatestproject.features.home.presentation.ui.components.shimmers.constants.ShimmersConstants

@Composable
internal fun FilmsShimmeredContainer(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .shimmer(),
            text = Constants.emptyString,
            style = FigmaTypography.bodyHalfMedium
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        repeat(ShimmersConstants.SHIMMERED_ELEMENTS_AMOUNT) { index ->
            FilmItemShimmered()

            if (index + 1 != ShimmersConstants.SHIMMERED_ELEMENTS_AMOUNT) {
                Spacer(modifier = Modifier.height(Dimens.spacingSmall))
            }
        }
    }
}

@Preview
@Composable
private fun FilmsShimmeredContainerPreview() {
    FilmsShimmeredContainer()
}