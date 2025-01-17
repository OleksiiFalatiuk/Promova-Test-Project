package com.example.login.presentation.ui.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.dispatchers.IoDispatcher
import com.example.common.response.ErrorData
import com.example.common.viewmodel.performUseCase
import com.example.domain.usecases.UpdateLocalUserUseCase
import com.example.login.domain.usecases.CreateGoogleSignInIntentUseCase
import com.example.login.domain.usecases.GetGoogleSignInDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val getGoogleSignInDataUseCase: GetGoogleSignInDataUseCase,
    private val getGoogleSignInIntentUseCase: CreateGoogleSignInIntentUseCase,
    private val updateLocalUserUseCase: UpdateLocalUserUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _sideEffect = Channel<SideEffect>()
    val sideEffect: Flow<SideEffect> get() = _sideEffect.receiveAsFlow()

    fun onGoogleSignInClicked(context: Context) {
        viewModelScope.launch(coroutineDispatcher) {
            val intent = getGoogleSignInIntentUseCase(context)
            _sideEffect.send(SideEffect.LaunchGoogleSignInIntent(intent))
        }
    }

    fun onGoogleSignInResult(intent: Intent) {
        viewModelScope.launch(coroutineDispatcher) {
            performUseCase(
                useCase = { getGoogleSignInDataUseCase(intent) },
                success = { user ->
                    updateLocalUserUseCase { user }
                },
                error = { error ->
                    showErrorSnackBar(error)
                }
            )
        }
    }

    private fun showErrorSnackBar(error: ErrorData) {
        viewModelScope.launch(coroutineDispatcher) {
            _sideEffect.send(
                SideEffect.ShowErrorSnackBar(
                    error.message.orEmpty()
                )
            )
        }
    }
}

internal sealed class SideEffect {
    data class LaunchGoogleSignInIntent(val intent: Intent) : SideEffect()
    data class ShowErrorSnackBar(val message: String) : SideEffect()
}