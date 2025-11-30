package com.techquantum.tasksync.modules.settings.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class SettingsRoute(val route: String) {
    object Settings : SettingsRoute("settings")
}
