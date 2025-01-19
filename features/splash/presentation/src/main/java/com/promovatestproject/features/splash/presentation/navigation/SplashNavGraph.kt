package com.promovatestproject.features.splash.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.promovatestproject.features.splash.presentation.ui.route.SplashRoute

fun NavGraphBuilder.splashGraph(parentRoute: String) {
    composable(route = parentRoute) {
        SplashRoute()
    }
}