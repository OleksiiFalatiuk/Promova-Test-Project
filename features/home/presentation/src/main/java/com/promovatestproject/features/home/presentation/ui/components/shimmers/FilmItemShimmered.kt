package com.promovatestproject.features.home.presentation.ui.components.shimmers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.core.localization.R
import com.example.designsystem.components.shimmer.shimmer
import com.example.designsystem.components.textbutton.TextButton
import com.example.designsystem.resources.constants.Constants
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicWhite
import com.example.designsystem.resources.figma.typography.FigmaTypography

@Composable
internal fun FilmItemShimmered(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = FigmaColors.basicWhite(),
                shape = RoundedCornerShape(Dimens.cornerRadiusTiny)
            )
            .padding(Dimens.spacingNormal)
    ) {
        Row(modifier = Modifier.height(Dimens.iconSizeExtraLarge)) {
            Box(
                modifier = modifier
                    .size(Dimens.iconSizeExtraLarge)
                    .clip(CircleShape)
                    .shimmer(shape = CircleShape)
            )

            Spacer(modifier = Modifier.width(Dimens.spacingSmall))

            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .shimmer(),
                    text = Constants.emptyString,
                    style = FigmaTypography.bodyBig
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.15f)
                        .shimmer(),
                    text = Constants.emptyString,
                    style = FigmaTypography.bodyHalfMedium,
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .shimmer(),
            text = Constants.emptyString,
            style = FigmaTypography.descriptionMedium,
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                text = stringResource(id = R.string.like_button_title).uppercase(),
                onClick = {
                    // do nothing
                }
            )

            Spacer(modifier = Modifier.width(Dimens.spacingNormal))

            TextButton(
                text = stringResource(id = R.string.share_button_title).uppercase(),
                onClick = {
                    // do nothing
                }
            )
        }
    }
}