package com.example.textrepeaterapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoutes {

    @Serializable
    data object SplashRoute : AppRoutes()

    @Serializable
    data object HomeRoute : AppRoutes()

    @Serializable
    data object TextRepeaterRoute : AppRoutes()

    @Serializable
    data object QuickRepliesRoute : AppRoutes()
}