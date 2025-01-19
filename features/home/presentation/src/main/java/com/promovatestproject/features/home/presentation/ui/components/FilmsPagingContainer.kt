package com.promovatestproject.features.home.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import com.example.designsystem.components.paging.PagingPullRefreshColumn
import com.example.designsystem.resources.dimens.Dimens
import com.promovatestproject.features.home.models.presentation.FilmPresentationModel
import com.promovatestproject.features.home.models.presentation.HomePresentationModel
import com.promovatestproject.features.home.presentation.ui.components.shimmers.FilmsShimmeredContainer
import kotlinx.coroutines.flow.Flow

@Composable
internal fun FilmsPagingContainer(
    modifier: Modifier = Modifier,
    pagingFilms: Flow<PagingData<HomePresentationModel>>,
    localFilms: List<HomePresentationModel>,
    showSnackbar: suspend (message: String) -> Unit,
    onLikeClick: (film: FilmPresentationModel) -> Unit,
    onDeleteClick: (film: FilmPresentationModel) -> Unit,
    onShareClick: (shareTitleOfFilm: String) -> Unit
) {
    PagingPullRefreshColumn(
        modifier = modifier,
        pagingDataFlow = pagingFilms,
        showSnackbar = showSnackbar,
        contentPadding = PaddingValues(Dimens.spacingNormal),
        verticalArrangement = Arrangement.spacedBy(Dimens.spacingBigSpecial),
        noItemsPlaceholder = {
            FilmsEmptyContainer(modifier = Modifier.fillParentMaxSize())
        },
        onLoadingError = {
            FilmsEmptyContainer(modifier = Modifier.fillParentMaxSize())
        },
        placeholderItemsNum = 3,
        placeholderItem = {
            FilmsShimmeredContainer()
        },
        item = { films ->
            FilmsContainer(
                films = films,
                localFilms = localFilms,
                onLikeClick = onLikeClick,
                onDeleteClick = onDeleteClick,
                onShareClick = onShareClick
            )
        }
    )
}