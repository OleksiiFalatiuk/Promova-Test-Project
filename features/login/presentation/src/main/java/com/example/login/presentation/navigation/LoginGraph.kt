package com.example.login.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.login.presentation.ui.route.LoginRoute

fun NavGraphBuilder.loginGraph(
    rootRoute: String,
    showSnackbar: suspend (message: String) -> Unit,
) {
    composable(route = rootRoute) {
        LoginRoute(
            viewModel = hiltViewModel(),
            showSnackbar = showSnackbar
        )
    }
}