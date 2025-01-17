package com.example.promovatesproject.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.designsystem.ui.snackbar.SnackbarScaffold
import com.example.promovatesproject.ui.navigation.destinations.RootDestination
import com.example.promovatesproject.ui.navigation.home.homeNavGraph
import com.example.promovatesproject.ui.navigation.login.loginNavGraph
import com.example.promovatesproject.ui.navigation.navhost.BaseNavHost
import com.example.promovatesproject.ui.viewmodel.MainViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun MainRoute(viewModel: MainViewModel) {
    val navController = rememberNavController()

    MainScreen(navController = navController)

    HandleSideEffects(
        sideEffect = viewModel.sideEffect,
        navController = navController
    )
}

@Composable
internal fun HandleSideEffects(
    sideEffect: Flow<MainViewModel.SideEffect>,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        sideEffect.collectLatest { sideEffectState ->
            val destination = when (sideEffectState) {
                MainViewModel.SideEffect.GoToLoginGraph -> RootDestination.LOGIN.route
                MainViewModel.SideEffect.GoToHomeGraph -> RootDestination.HOME.route
            }

            // if destination == currentBackStackEntry.destination do nothing
            if (navController.currentBackStackEntry?.destination?.route == destination) {
                return@collectLatest
            }

            navController.navigate(destination) { popUpTo(RootDestination.ROOT.route) }
        }
    }
}

@Composable
internal fun MainScreen(navController: NavHostController) {
    SnackbarScaffold(
        content = { _, snackbarHostState ->
            BaseNavHost(
                navController = navController,
                startDestination = RootDestination.ROOT.route
            ) {
                navigation(
                    route = RootDestination.ROOT.route,
                    startDestination = RootDestination.LOGIN.route
                ) {
                    loginNavGraph(
                        parentRoute = RootDestination.LOGIN.route,
                        showSnackbar = snackbarHostState.value::showSnackbar
                    )

                    homeNavGraph(
                        parentRoute = RootDestination.HOME.route,
                        showSnackbar = snackbarHostState.value::showSnackbar
                    )
                }
            }
        }
    )
}