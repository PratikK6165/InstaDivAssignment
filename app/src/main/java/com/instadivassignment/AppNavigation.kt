package com.instadivassignment

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController, startDestination = "tagCloud") {
        composable("tagCloud") {
            // Pass the NavController and ViewModel to TagCloudScreen
            TagCloudScreen(navController = navController)
        }
        composable(
            route = "nextScreen/{selectedTag}", // This route expects the selectedTag argument
            arguments = listOf(navArgument("selectedTag") { type = NavType.StringType })
        ) { backStackEntry ->
            val selectedTag = backStackEntry.arguments?.getString("selectedTag")
            selectedTag?.let {
                NextScreen(tag = it)
            }
        }
    }
}


