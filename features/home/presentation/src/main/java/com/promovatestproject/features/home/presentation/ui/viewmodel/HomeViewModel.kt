package com.promovatestproject.features.home.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.promovatestproject.features.home.domain.usecases.GetPagingFilmsFromRemoteUseCase
import com.promovatestproject.features.home.models.presentation.HomePresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.common.dispatchers.IoDispatcher
import com.example.domain.usecases.GetLocalUserFlowUseCase
import com.promovatesproject.shared.user.models.presentation.UserPresentationModel
import com.promovatesproject.shared.user.models.presentation.toPresentationModel
import com.promovatestproject.features.home.domain.usecases.DeleteFilmFromLocalUseCase
import com.promovatestproject.features.home.domain.usecases.GetLikedFilmsFromLocalUseCase
import com.promovatestproject.features.home.domain.usecases.InsertFilmToLocalUseCase
import com.promovatestproject.features.home.models.presentation.FilmPresentationModel
import com.promovatestproject.features.home.models.presentation.toDomainModel
import com.promovatestproject.features.home.models.presentation.toPresentationModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getLocalUserFlowUseCase: GetLocalUserFlowUseCase,
    private val getPagingFilmsFromRemoteUseCase: GetPagingFilmsFromRemoteUseCase,
    private val getLikedFilmsFromLocalUseCase: GetLikedFilmsFromLocalUseCase,
    private val insertFilmToLocalUseCase: InsertFilmToLocalUseCase,
    private val deleteFilmFromLocalUseCase: DeleteFilmFromLocalUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _sideEffect = Channel<SideEffect>()
    val sideEffect: Flow<SideEffect> get() = _sideEffect.receiveAsFlow()

    private val _uiState = MutableStateFlow(
        HomeViewModelState(
            user = null,
            films = flowOf(),
            filmsFromLocal = emptyList()
        )
    )
    val uiState: StateFlow<HomeViewModelState> = _uiState

    init {
        collectUser()
        getHomeFilmsFromRemote()
        getHomeFilmsFromLocal()
    }

    private fun collectUser() {
        viewModelScope.launch(coroutineDispatcher) {
            getLocalUserFlowUseCase().collectLatest { localUser ->
                _uiState.update { uiState ->
                    uiState.copy(
                        user = localUser?.toPresentationModel()
                    )
                }
            }
        }
    }

    private fun getHomeFilmsFromRemote() {
        _uiState.update { uiState ->
            uiState.copy(
                films = getPagingFilmsFromRemoteUseCase().map { pagingData ->
                    pagingData.map { it.toPresentationModel() }
                }.cachedIn(viewModelScope)
            )
        }
    }

    private fun getHomeFilmsFromLocal() {
        viewModelScope.launch(coroutineDispatcher) {
            getLikedFilmsFromLocalUseCase().collectLatest { filmsFromLocal ->
                _uiState.update { uiState ->
                    uiState.copy(
                        filmsFromLocal = filmsFromLocal.map { it.toPresentationModel() }
                    )
                }
            }
        }
    }

    fun likeFilm(film: FilmPresentationModel) {
        viewModelScope.launch(coroutineDispatcher) {
            insertFilmToLocalUseCase(film = film.toDomainModel())
        }
    }

    fun deleteLikedFilm(film: FilmPresentationModel) {
        viewModelScope.launch(coroutineDispatcher) {
            deleteFilmFromLocalUseCase(film = film.toDomainModel())
        }
    }

    fun showSnackbar(message: String) {
        viewModelScope.launch(coroutineDispatcher) {
            _sideEffect.send(SideEffect.ShowSnackbar(message))
        }
    }
}

internal sealed class SideEffect {
    data class ShowSnackbar(val message: String) : SideEffect()
}

internal data class HomeViewModelState(
    val user: UserPresentationModel?,
    val films: Flow<PagingData<HomePresentationModel>>,
    val filmsFromLocal: List<HomePresentationModel>
)