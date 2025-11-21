package com.techquantum.tasksync.modules.notifications.navigation

sealed class NotificationsRoute(val route: String) {
    object Notifications : NotificationsRoute("notifications")
}

