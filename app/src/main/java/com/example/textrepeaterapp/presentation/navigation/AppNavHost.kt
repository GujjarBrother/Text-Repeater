package com.example.textrepeaterapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.textrepeaterapp.presentation.home.HomeScreen
import com.example.textrepeaterapp.presentation.quickReplies.QuickRepliesScreen
import com.example.textrepeaterapp.presentation.splash.SplashScreen
import com.example.textrepeaterapp.presentation.textRepeater.TextRepeaterScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    val navigationActions = NavigationActions(navHostController = navHostController)

    NavHost(
        navController = navHostController,
        startDestination = AppRoutes.SplashRoute
    ) {
        composable<AppRoutes.SplashRoute> {
            SplashScreen(
                navigateToNext = {
                    navigateAhead(
                        targetAppRoute = AppRoutes.HomeRoute,
                        navigationActions = navigationActions
                    )
                }
            )
        }

        composable<AppRoutes.HomeRoute> {
            HomeScreen(
                textRepeaterCallback = {
                    navigateAhead(
                        targetAppRoute = AppRoutes.TextRepeaterRoute,
                        navigationActions = navigationActions
                    )
                },
                quickRepliesCallback = {
                    navigateAhead(
                        targetAppRoute = AppRoutes.QuickRepliesRoute,
                        navigationActions = navigationActions
                    )
                }
            )
        }

        composable<AppRoutes.TextRepeaterRoute> {
            TextRepeaterScreen(
                onBackClickCallBack = {
                    navigationActions.goBack.invoke()
                }
            )
        }

        composable<AppRoutes.QuickRepliesRoute> {
            QuickRepliesScreen()
        }
    }
}

private fun navigateAhead(
    targetAppRoute: AppRoutes,
    navigationActions: NavigationActions
) {
    when (targetAppRoute) {
        AppRoutes.SplashRoute -> {}
        AppRoutes.HomeRoute -> navigationActions.navigateToHomeScreen.invoke()
        AppRoutes.TextRepeaterRoute -> navigationActions.navigateToTextRepeaterScreen.invoke()
        AppRoutes.QuickRepliesRoute -> navigationActions.navigateToQuickRepliesScreen.invoke()
    }
}