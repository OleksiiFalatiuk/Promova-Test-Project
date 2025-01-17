package com.promovatestproject.features.home.presentation.ui.route

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
        showSnackbar = viewModel::showSnackbar
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
    LaunchedEffect(Unit) {
        sideEffect.collectLatest { sideEffectState ->
            when (sideEffectState) {
                is SideEffect.ShowSnackbar -> {
                    showSnackbar(sideEffectState.message)
                }
            }
        }
    }
}

@Composable
internal fun HomeScreen(
    uiState: HomeViewModelState,
    showSnackbar: (message: String) -> Unit
) {
    Column(
        modifier = Modifier.systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "AAAAAAAAAA"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeViewModelState(
            homeFlow = flowOf()
        ),
        showSnackbar = {}
    )
}