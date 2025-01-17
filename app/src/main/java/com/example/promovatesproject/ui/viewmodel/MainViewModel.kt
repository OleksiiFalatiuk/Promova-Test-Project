package com.example.promovatesproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.IoDispatcher
import com.example.domain.usecases.GetLocalUserFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLocalUserFlowUseCase: GetLocalUserFlowUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _sideEffect = Channel<SideEffect>()
    val sideEffect: Flow<SideEffect> get() = _sideEffect.receiveAsFlow()

    init {
        viewModelScope.launch(coroutineDispatcher) {
            collectUserFlow()
        }
    }

    private suspend fun collectUserFlow() {
        getLocalUserFlowUseCase()
            .collectLatest { user ->
                when {
                    user == null -> {
                        _sideEffect.send(SideEffect.GoToLoginGraph)
                    }

                    else -> {
                        _sideEffect.send(SideEffect.GoToHomeGraph)
                    }
                }
            }
    }

    sealed class SideEffect {
        data object GoToLoginGraph : SideEffect()
        data object GoToHomeGraph : SideEffect()
    }
}