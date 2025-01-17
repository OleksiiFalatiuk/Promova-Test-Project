package com.promovatestproject.features.home.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.promovatestproject.features.home.domain.usecases.GetHomeUseCase
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
import com.example.common.dispatchers.IoDispatcher

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getHomeOnRemoteUseCase: GetHomeUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _sideEffect = Channel<SideEffect>()
    val sideEffect: Flow<SideEffect> get() = _sideEffect.receiveAsFlow()

    private val _uiState = MutableStateFlow(
        HomeViewModelState(
            homeFlow = flowOf()
        )
    )
    val uiState: StateFlow<HomeViewModelState> = _uiState

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
    val homeFlow: Flow<PagingData<HomePresentationModel>>
)