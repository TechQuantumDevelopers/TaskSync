package com.techquantum.tasksync.modules.dashboard.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class DashboardRoute(val route: String) {
    object Dashboard : DashboardRoute("dashboard")
}
