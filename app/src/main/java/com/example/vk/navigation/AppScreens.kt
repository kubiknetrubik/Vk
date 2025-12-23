package com.example.vk.navigation

sealed class AppScreens(val route: String){
    object SignUpScreen : AppScreens("sign_up")
    object AppleRegistrationScreen : AppScreens("apple_registration")
    object GoogleRegistrationScreen : AppScreens("google_registration")
    object VkRegistrationScreen : AppScreens("vk_registration")
    object EmailRegistrationScreen : AppScreens("email_registration")
    object WelcomeScreen : AppScreens("welcome")
    object WelcomeScreenEmail : AppScreens("welcome/{login}/{email}/{password}")
    object FirstEntryScreen : AppScreens("first_entry")
    object FirstEntryScreenEmail : AppScreens("first_entry/{login}/{email}/{password}")
    object SettingsScreenEmail : AppScreens("settings/{login}/{email}/{password}")
    object SettingsScreen : AppScreens("settings")

}