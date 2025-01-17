package com.example.login.presentation.navigation

import com.example.common.navigation.Router

object LoginRouter : Router {
    override fun route(parentRoute: String): String = "${parentRoute}_login"

    override fun navigate(
        parentRoute: String,
        navController: androidx.navigation.NavController,
        args: Router.NavArgs?,
        navOptions: androidx.navigation.NavOptions?
    ) {
        navController
            .navigate(
                route = route(parentRoute),
                navOptions = navOptions
            )
    }
}