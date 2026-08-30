package com.example.textrepeaterapp.presentation.navigation

import androidx.navigation.NavHostController

class NavigationActions(
    private val navHostController: NavHostController
) {
    val goBack: () -> Unit = {
        navHostController.popBackStack()
    }

    val navigateToHomeScreen: () -> Unit = {
        navHostController.navigate(route = AppRoutes.HomeRoute) {
            popUpTo(AppRoutes.SplashRoute) {
                inclusive = true
            }
        }
    }

    val navigateToTextRepeaterScreen: () -> Unit = {
        navHostController.navigate(route = AppRoutes.TextRepeaterRoute)
    }

    val navigateToQuickRepliesScreen: () -> Unit = {
        navHostController.navigate(route = AppRoutes.QuickRepliesRoute)
    }
}