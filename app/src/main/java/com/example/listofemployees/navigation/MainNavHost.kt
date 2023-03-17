package com.example.listofemployees.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            onNavigateToDetails = {
                navController.navigate(route = Screen.Details.route)
            }
        )
    }
    composable(
        route = Screen.Details.route
    ) {
        DetailsScreen()
    }
}