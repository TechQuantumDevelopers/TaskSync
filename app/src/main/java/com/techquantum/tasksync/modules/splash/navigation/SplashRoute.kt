package com.techquantum.tasksync.modules.splash.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class SplashRoute (val route: String) {
    object Splash : SplashRoute("splash")
}