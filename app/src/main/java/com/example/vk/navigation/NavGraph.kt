package com.example.vk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vk.datacontrol.TasksRepository
import com.example.vk.ui.main.FirstEntryScreen
import com.example.vk.ui.main.TaskViewModel
import com.example.vk.ui.registration.AppleRegistrationScreen
import com.example.vk.ui.registration.EmailRegistrationScreen
import com.example.vk.ui.registration.GoogleRegistrationScreen
import com.example.vk.ui.registration.VkRegistrationScreen
import com.example.vk.ui.signup.SignUpScreen
import com.example.vk.ui.welcome.WelcomeScreen
import com.example.vk.ui.settings.SettingsScreen
import android.util.Log
@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val vm: TaskViewModel = viewModel(
        initializer = { TaskViewModel(TasksRepository(context))}
    )
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
        composable(route = AppScreens.WelcomeScreenEmail.route) {backStackEntry ->
            val login = backStackEntry.arguments?.getString("login")
            val email = backStackEntry.arguments?.getString("email")
            val password = backStackEntry.arguments?.getString("password")


            WelcomeScreen(navController = navController,login,email,password)
        }
        composable(route = AppScreens.FirstEntryScreen.route) {
            FirstEntryScreen(vm,onNavigatetoSettings = {
                navController.navigate(AppScreens.SettingsScreen.route)
            })
        }
        composable(route = AppScreens.FirstEntryScreenEmail.route) {backStackEntry ->
            val login = backStackEntry.arguments?.getString("login")
            val email = backStackEntry.arguments?.getString("email")
            val password = backStackEntry.arguments?.getString("password")
            FirstEntryScreen(vm,onNavigatetoSettings = {
                navController.navigate("settings/$login/$email/$password")
            })
        }
        composable(route = AppScreens.SettingsScreenEmail.route){backStackEntry ->
            val login = backStackEntry.arguments?.getString("login")
            val email = backStackEntry.arguments?.getString("email")
            val password = backStackEntry.arguments?.getString("password")
            SettingsScreen(onNavigatetoTasks = {navController.navigate("first_entry/$login/$email/$password")},login = login,email = email,password =password)
        }
        composable(route = AppScreens.SettingsScreen.route){
            SettingsScreen(onNavigatetoTasks = {navController.navigate(AppScreens.FirstEntryScreen.route)})
        }
    }
}
