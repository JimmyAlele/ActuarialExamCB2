package com.example.actuarialexams.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


/**
 * This is the navigation graph file.
 * This file outlines the routes.
 * On creation of the app, the Nav() functions is called (see MainActivity file).
 * The Nav() functions sets the start screen as route HomeScreen which is HomeScreen
 */
@Composable
fun Nav() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "home screen") {

        composable(route = "home screen"){
            HomeScreen(navController)
        }

        composable(route = "paper selection screen"){
            PaperSelectionScreen(navController)
        }

        composable(route = "results screen"){
            ResultsScreen(navController)
        }
    }
}