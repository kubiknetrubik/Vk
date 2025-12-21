package com.example.vk.ui.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.vk.R
import com.example.vk.ui.components.RegistrationScreenTemplate

@Composable
fun AppleRegistrationScreen(navController: NavController) {
    RegistrationScreenTemplate(
        logoResId = R.drawable.apple_big_logo,
        onContinueClick = {
            // TODO: Implement Apple registration logic

        }
    )
}