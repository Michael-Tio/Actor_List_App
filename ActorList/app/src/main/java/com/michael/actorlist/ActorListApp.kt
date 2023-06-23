package com.michael.actorlist

import com.michael.actorlist.ui.screen.profile.ProfileScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.michael.actorlist.ui.navigation.Screen
import com.michael.actorlist.ui.screen.detail.DetailScreen
import com.michael.actorlist.ui.screen.home.HomeScreen

@Composable
fun ActorListApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = { actorId ->
                    navController.navigate(Screen.Detail.createRoute(actorId))
                },
                navigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("actorId") { type = NavType.LongType }),
        ) {
            val id = it.arguments?.getLong("actorId") ?: -1L
            DetailScreen(
                actorId = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}