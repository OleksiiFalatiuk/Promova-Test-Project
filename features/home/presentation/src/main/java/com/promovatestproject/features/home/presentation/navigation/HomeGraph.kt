package com.promovatestproject.features.home.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.promovatestproject.features.home.presentation.ui.route.HomeRoute

fun NavGraphBuilder.homeGraph(
    rootRoute: String,
    showSnackbar: suspend (message: String) -> Unit
) {
    composable(route = rootRoute) {
        HomeRoute(
            viewModel = hiltViewModel(),
            showSnackbar = showSnackbar
        )
    }
}