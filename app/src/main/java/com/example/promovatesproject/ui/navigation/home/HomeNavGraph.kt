package com.example.promovatesproject.ui.navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.promovatesproject.ui.navigation.navhost.BaseNavHost
import com.promovatestproject.features.home.presentation.navigation.HomeRouter
import com.promovatestproject.features.home.presentation.navigation.homeGraph

fun NavGraphBuilder.homeNavGraph(
    parentRoute: String,
    showSnackbar: suspend (message: String) -> Unit
) {
    composable(parentRoute) {
        val navController = rememberNavController()
        val startDestination = HomeRouter.route(parentRoute)

        BaseNavHost(
            navController,
            startDestination = startDestination
        ) {
            homeGraph(
                rootRoute = HomeRouter.route(parentRoute = parentRoute),
                showSnackbar = showSnackbar
            )
        }
    }
}