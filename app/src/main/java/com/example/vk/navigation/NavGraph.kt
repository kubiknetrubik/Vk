package com.example.vk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vk.ui.main.FirstEntryScreen
import com.example.vk.ui.registration.AppleRegistrationScreen
import com.example.vk.ui.registration.EmailRegistrationScreen
import com.example.vk.ui.registration.GoogleRegistrationScreen
import com.example.vk.ui.registration.VkRegistrationScreen
import com.example.vk.ui.signup.SignUpScreen
import com.example.vk.ui.welcome.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.SignUpScreen.route
    ) {
        composable(route = AppScreens.SignUpScreen.route) {
            SignUpScreen(
                onNavigateToApple = {
                    navController.navigate(AppScreens.AppleRegistrationScreen.route)
                },
                onNavigateToGoogle = {
                    navController.navigate(AppScreens.GoogleRegistrationScreen.route)
                },
                onNavigateToVk = {
                    navController.navigate(AppScreens.VkRegistrationScreen.route)
                },
                onNavigateToEmail = {
                    navController.navigate(AppScreens.EmailRegistrationScreen.route)
                },
                onSkipClick = {
                    navController.navigate(AppScreens.WelcomeScreen.route)
                }
            )
        }

        composable(route = AppScreens.AppleRegistrationScreen.route) {
            AppleRegistrationScreen(navController = navController)
        }

        composable(route = AppScreens.GoogleRegistrationScreen.route) {
            GoogleRegistrationScreen(navController = navController)
        }

        composable(route = AppScreens.VkRegistrationScreen.route) {
            VkRegistrationScreen(navController = navController)
        }

        composable(route = AppScreens.EmailRegistrationScreen.route) {
            EmailRegistrationScreen(navController = navController)
        }

        composable(route = AppScreens.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }

        composable(route = AppScreens.FirstEntryScreen.route) {
            FirstEntryScreen()
        }
    }
}
