package com.example.listofemployees.navigation

sealed class Screen(
    val route: String
) {
    object Main : Screen(
        route = "main_route"
    )

    object Details : Screen(
        route = "details_route"
    ) {
        fun getUserArgs() = "userArgs"
    }
}