package com.promovatestproject.features.home.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.designsystem.resources.dimens.Dimens
import com.promovatestproject.features.home.models.presentation.FilmPresentationModel
import com.promovatestproject.features.home.models.presentation.HomePresentationModel

@Composable
internal fun FilmsLocalContainer(
    modifier: Modifier = Modifier,
    localFilms: List<HomePresentationModel>,
    onLikeClick: (film: FilmPresentationModel) -> Unit,
    onDeleteClick: (film: FilmPresentationModel) -> Unit,
    onShareClick: (shareTitleOfFilm: String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        if (localFilms.isEmpty()) {
            FilmsEmptyContainer(modifier = Modifier.fillMaxSize())
        } else {
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(Dimens.spacingNormal),
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingBigSpecial)
            ) {
                items(localFilms) { films ->
                    FilmsContainer(
                        films = films,
                        localFilms = localFilms,
                        onLikeClick = onLikeClick,
                        onDeleteClick = onDeleteClick,
                        onShareClick = onShareClick
                    )
                }
            }
        }
    }
}