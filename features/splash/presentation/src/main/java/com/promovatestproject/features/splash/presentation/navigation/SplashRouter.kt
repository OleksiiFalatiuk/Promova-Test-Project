package com.promovatestproject.features.splash.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.common.navigation.Router

object SplashRouter : Router {
    override fun route(parentRoute: String): String = "${parentRoute}_splash"

    override fun navigate(
        parentRoute: String,
        navController: NavController,
        args: Router.NavArgs?,
        navOptions: NavOptions?
    ) {
        navController.navigate(route = route(parentRoute), navOptions = navOptions)
    }
}