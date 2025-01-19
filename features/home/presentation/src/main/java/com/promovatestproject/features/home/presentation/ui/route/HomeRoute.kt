package com.promovatestproject.features.home.presentation.ui.route

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.intent.IntentHelper
import com.example.designsystem.components.pager.TabsPager
import com.example.designsystem.resources.dimens.Dimens
import com.example.designsystem.resources.figma.colors.FigmaColors
import com.example.designsystem.resources.figma.colors.basicBackground
import com.example.designsystem.resources.figma.colors.basicPurple
import com.promovatestproject.features.home.models.presentation.FilmPresentationModel
import com.promovatestproject.features.home.models.presentation.HomeTabsTypePresentationModel
import com.promovatestproject.features.home.presentation.ui.components.AvatarImage
import com.promovatestproject.features.home.presentation.ui.components.FilmsLocalContainer
import com.promovatestproject.features.home.presentation.ui.components.FilmsPagingContainer
import com.promovatestproject.features.home.presentation.ui.viewmodel.HomeViewModel
import com.promovatestproject.features.home.presentation.ui.viewmodel.HomeViewModelState
import com.promovatestproject.features.home.presentation.ui.viewmodel.SideEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel,
    showSnackbar: suspend (message: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        showSnackbar = viewModel::showSnackbar,
        onLikeClick = viewModel::likeFilm,
        onDeleteClick = viewModel::deleteLikedFilm,
        onShareClick = viewModel::shareFilm
    )

    HandleSideEffects(
        sideEffect = viewModel.sideEffect,
        showSnackbar = showSnackbar
    )
}

@Composable
internal fun HandleSideEffects(
    sideEffect: Flow<SideEffect>,
    showSnackbar: suspend (message: String) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        sideEffect.collectLatest { sideEffectState ->
            when (sideEffectState) {
                is SideEffect.ShowSnackbar -> {
                    showSnackbar(sideEffectState.message)
                }

                is SideEffect.ShareFilm -> {
                    IntentHelper.sharePlainText(
                        context = context,
                        text = sideEffectState.shareTitleOfFilm
                    )
                }
            }
        }
    }
}

@Composable
internal fun HomeScreen(
    uiState: HomeViewModelState,
    showSnackbar: (message: String) -> Unit,
    onLikeClick: (film: FilmPresentationModel) -> Unit,
    onDeleteClick: (film: FilmPresentationModel) -> Unit,
    onShareClick: (shareTitleOfFilm: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = FigmaColors.basicPurple())
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            AvatarImage(
                modifier = Modifier
                    .padding(
                        top = Dimens.spacingNormal,
                        end = Dimens.spacingNormal
                    ),
                avatarImage = uiState.user?.profileImageUrl
            )

            Spacer(modifier = Modifier.height(Dimens.spacingBigSpecial))
        }

        TabsPager(
            modifier = Modifier.fillMaxSize(),
            tabRowModifier = Modifier.fillMaxWidth(),
            pagerModifier = Modifier
                .fillMaxHeight()
                .background(color = FigmaColors.basicBackground()),
            pages = HomeTabsTypePresentationModel.entries.map { tab ->
                stringResource(id = tab.title).uppercase()
            },
            beyondBoundsPageCount = 1,
            initialPage = 0
        ) { pageIndex ->
            when (pageIndex) {
                HomeTabsTypePresentationModel.FILMS.ordinal -> {
                    FilmsPagingContainer(
                        pagingFilms = uiState.films,
                        localFilms = uiState.filmsFromLocal,
                        showSnackbar = showSnackbar,
                        onLikeClick = onLikeClick,
                        onDeleteClick = onDeleteClick,
                        onShareClick = onShareClick
                    )
                }

                HomeTabsTypePresentationModel.FAVOURITES.ordinal -> {
                    FilmsLocalContainer(
                        localFilms = uiState.filmsFromLocal.sortedByDescending { it.releaseDate },
                        onLikeClick = onLikeClick,
                        onDeleteClick = onDeleteClick,
                        onShareClick = onShareClick
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeViewModelState(
            user = null,
            films = flowOf(),
            filmsFromLocal = emptyList()
        ),
        showSnackbar = {},
        onLikeClick = {},
        onDeleteClick = {},
        onShareClick = {}
    )
}