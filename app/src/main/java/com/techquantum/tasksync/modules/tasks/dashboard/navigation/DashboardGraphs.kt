package com.techquantum.tasksync.modules.tasks.dashboard.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class DashboardGraphs (val route: String) {
    object Dashboard : DashboardGraphs("dashboard")
}