package com.promovatestproject.features.home.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.components.textbutton.TextButton
import com.example.designsystem.resources.constants.Constants
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicBlack
import com.example.designsystem.resources.figma.colors.basicWhite
import com.example.designsystem.resources.figma.typography.FigmaTypography
import com.promovatestproject.features.home.models.presentation.FilmPresentationModel
import java.util.Locale
import com.example.core.localization.R as LocalizableResources

@Composable
internal fun FilmItem(
    modifier: Modifier = Modifier,
    film: FilmPresentationModel,
    isFilmLiked: Boolean,
    onLikeClick: (film: FilmPresentationModel) -> Unit,
    onDeleteClick: (film: FilmPresentationModel) -> Unit,
    onShareClick: (shareTitleOfFilm: String) -> Unit
) {
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
            AvatarImage(
                imageSize = Dimens.iconSizeExtraLarge,
                avatarImage = film.posterPath
            )

            Spacer(modifier = Modifier.width(Dimens.spacingSmall))

            Column {
                Text(
                    text = film.title,
                    color = FigmaColors.basicBlack(),
                    style = FigmaTypography.bodyBig,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = String.format(Locale.US, Constants.oneNumeralAfterDot, film.voteAverage),
                    color = FigmaColors.basicBlack(),
                    style = FigmaTypography.bodyHalfMedium,
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        Text(
            text = film.overview,
            color = FigmaColors.basicBlack().copy(alpha = 0.6f),
            style = FigmaTypography.descriptionMedium,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                text = stringResource(
                    id = if (isFilmLiked) {
                        LocalizableResources.string.delete_button_title
                    } else {
                        LocalizableResources.string.like_button_title
                    }
                ).uppercase(),
                onClick = {
                    if (isFilmLiked) {
                        onDeleteClick(film)
                    } else {
                        onLikeClick(film)
                    }
                }
            )

            Spacer(modifier = Modifier.width(Dimens.spacingNormal))

            TextButton(
                text = stringResource(id = LocalizableResources.string.share_button_title).uppercase(),
                onClick = {
                    onShareClick("${film.title}\n${film.overview}")
                }
            )
        }
    }
}

@Preview
@Composable
private fun FilmItemPreview() {
    FilmItem(
        modifier = Modifier.fillMaxWidth(),
        film = FilmPresentationModel(
            id = 1,
            title = "Pulp Fiction",
            overview = "American neo-noir black comedy crime film written and directed by Quentin Tarantino",
            posterPath = null,
            voteAverage = 6.1,
            releaseDate = ""
        ),
        isFilmLiked = true,
        onLikeClick = {},
        onDeleteClick = {},
        onShareClick = {}
    )
}