package com.techquantum.tasksync.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.techquantum.tasksync.modules.dashboard.navigation.DashboardRoute
import com.techquantum.tasksync.modules.dashboard.ui.DashboardScreen
import com.techquantum.tasksync.modules.notifications.navigation.NotificationsRoute
import com.techquantum.tasksync.modules.notifications.ui.NotificationsScreen
import com.techquantum.tasksync.modules.onboarding.navigation.OnboardingRoute
import com.techquantum.tasksync.modules.onboarding.ui.OnboardingScreen
import com.techquantum.tasksync.modules.settings.navigation.SettingsRoute
import com.techquantum.tasksync.modules.settings.ui.SettingsScreen
import com.techquantum.tasksync.modules.splash.navigation.SplashRoute
import com.techquantum.tasksync.modules.splash.ui.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SplashRoute.Splash.route
    ){
        composable(route = SplashRoute.Splash.route){
            SplashScreen(navController = navController)
        }

        composable(route = OnboardingRoute.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }

        composable(route = DashboardRoute.Dashboard.route) {
            DashboardScreen()
        }

        composable(route = NotificationsRoute.Notifications.route) {
            NotificationsScreen()
        }

        composable(route = SettingsRoute.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}