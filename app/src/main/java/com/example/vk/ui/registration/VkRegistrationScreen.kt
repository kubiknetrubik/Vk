package com.example.vk.ui.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.vk.R
import com.example.vk.navigation.AppScreens
import com.example.vk.ui.components.RegistrationScreenTemplate

@Composable
fun VkRegistrationScreen(navController: NavController) {
    RegistrationScreenTemplate(
        logoResId = R.drawable.vk_big_logo,
        onContinueClick = {
            navController.navigate(
                AppScreens.WelcomeScreen.route)
        }
    )
}