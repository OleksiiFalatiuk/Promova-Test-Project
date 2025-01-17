package com.example.promovatesproject.ui.navigation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.presentation.navigation.LoginRouter
import com.example.login.presentation.navigation.loginGraph
import com.example.promovatesproject.ui.navigation.navhost.BaseNavHost

fun NavGraphBuilder.loginNavGraph(
    parentRoute: String,
    showSnackbar: suspend (message: String) -> Unit
) {
    composable(parentRoute) {
        val navController = rememberNavController()
        val startDestination = LoginRouter.route(parentRoute)

        BaseNavHost(
            navController,
            startDestination = startDestination
        ) {
            loginGraph(
                rootRoute = LoginRouter.route(parentRoute = parentRoute),
                showSnackbar = showSnackbar
            )
        }
    }
}