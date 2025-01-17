package com.promovatestproject.features.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.common.navigation.Router

object HomeRouter : Router {
    override fun route(parentRoute: String): String = "${parentRoute}_home"

    override fun navigate(
        parentRoute: String,
        navController: NavController,
        args: Router.NavArgs?,
        navOptions: NavOptions?
    ) {
        navController.navigate(route = route(parentRoute), navOptions = navOptions)
    }
}