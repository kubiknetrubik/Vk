package com.example.vk.navigation

sealed class AppScreens(val route: String){
    object SignUpScreen : AppScreens("sign_up")
    object AppleRegistrationScreen : AppScreens("apple_registration")
    object GoogleRegistrationScreen : AppScreens("google_registration")
    object VkRegistrationScreen : AppScreens("vk_registration")
    object EmailRegistrationScreen : AppScreens("email_registration")
    object WelcomeScreen : AppScreens("welcome")
    object FirstEntryScreen : AppScreens("first_entry")
}