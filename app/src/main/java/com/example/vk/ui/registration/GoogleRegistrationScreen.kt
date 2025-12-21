package com.example.vk.ui.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.vk.R
import com.example.vk.ui.components.RegistrationScreenTemplate

@Composable
fun GoogleRegistrationScreen(navController: NavController) {
    RegistrationScreenTemplate(
        logoResId = R.drawable.google_big_logo,
        onContinueClick = {
            // TODO: Implement Google registration logic

        }
    )
}