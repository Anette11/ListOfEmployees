package com.example.listofemployees.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.data.remote.Item
import com.example.listofemployees.ui.screens.details.DetailsScreen
import com.example.listofemployees.ui.screens.main.MainScreen

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Main.route
) = NavHost(
    navController = navController,
    startDestination = startDestination
) {
    composable(
        route = Screen.Main.route
    ) {
        MainScreen(
            onNavigateToDetails = { user ->
                val userToJson = user.toJson()
                navController.navigate(route = Screen.Details.route.plus("/$userToJson"))
            }
        )
    }
    composable(
        route = Screen.Details.route.plus("/{${Screen.Details.getUserArgs()}}"),
        arguments = listOf(
            navArgument(
                name = Screen.Details.getUserArgs()
            ) {
                type = UserNavType()
            }
        )
    ) { navBackStackEntry ->
        val user = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> navBackStackEntry.arguments?.getParcelable(
                Screen.Details.getUserArgs(),
                Item::class.java
            )
            else -> navBackStackEntry.arguments?.getParcelable(Screen.Details.getUserArgs())
        }
        user?.let {
            DetailsScreen(
                user = user,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}