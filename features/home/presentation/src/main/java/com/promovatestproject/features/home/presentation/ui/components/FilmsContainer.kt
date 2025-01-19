package com.promovatestproject.features.home.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicBlack
import com.example.designsystem.resources.figma.typography.FigmaTypography
import com.promovatestproject.features.home.models.presentation.FilmPresentationModel
import com.promovatestproject.features.home.models.presentation.HomePresentationModel

@Composable
internal fun FilmsContainer(
    modifier: Modifier = Modifier,
    films: HomePresentationModel,
    localFilms: List<HomePresentationModel>,
    onLikeClick: (film: FilmPresentationModel) -> Unit,
    onDeleteClick: (film: FilmPresentationModel) -> Unit,
    onShareClick: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = films.releaseDate,
            color = FigmaColors.basicBlack(),
            style = FigmaTypography.bodyHalfMedium
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        films.films.forEachIndexed { index, film ->
            FilmItem(
                film = film,
                isFilmLiked = localFilms.any { it.films.any { it.id == film.id } },
                onLikeClick = onLikeClick,
                onDeleteClick = onDeleteClick,
                onShareClick = onShareClick
            )

            if (index + 1 != films.films.size) {
                Spacer(modifier = Modifier.height(Dimens.spacingSmall))
            }
        }
    }
}

@Preview
@Composable
private fun FilmsContainerPreview() {
    FilmsContainer(
        films = HomePresentationModel(
            releaseDate = "Feb 2021",
            films = listOf(
                FilmPresentationModel(
                    id = 1,
                    title = "Pulp Fiction",
                    overview = "American neo-noir black comedy crime film written and directed by Quentin Tarantino",
                    posterPath = null,
                    voteAverage = 6.1,
                    releaseDate = ""
                )
            )
        ),
        localFilms = emptyList(),
        onLikeClick = {},
        onDeleteClick = {},
        onShareClick = {}
    )
}