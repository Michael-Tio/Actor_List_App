package com.michael.actorlist.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Detail : Screen("home/{actorId}") {
        fun createRoute(actorId: Long) = "home/$actorId"
    }
}
