package com.techquantum.tasksync.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.techquantum.tasksync.modules.tasks.dashboard.navigation.DashboardGraphs
import com.techquantum.tasksync.modules.tasks.dashboard.ui.DashboardScreen
import com.techquantum.tasksync.modules.splash.navigation.SplashRoute
import com.techquantum.tasksync.modules.splash.ui.SplashScreen
import com.techquantum.tasksync.modules.tasks.addUpdate.navigation.TasksGraphs
import com.techquantum.tasksync.modules.tasks.addUpdate.ui.AddUpdateTasksScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = SplashRoute.Splash.route
    ){
        composable(route = SplashRoute.Splash.route){
            SplashScreen(navController = navController)
        }

        composable(route = DashboardGraphs.Dashboard.route){
            DashboardScreen(navController = navController)
        }

        // Add (no taskId)
        composable(route = TasksGraphs.Tasks().route) {
            AddUpdateTasksScreen(taskId = null, navController = navController)
        }

        // Edit (with taskId argument)
        composable(
            route = TasksGraphs.Tasks.ROUTE_WITH_ARG,
            arguments = listOf(navArgument(TasksGraphs.Tasks.ARG_TASK_ID) { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString(TasksGraphs.Tasks.ARG_TASK_ID)
            AddUpdateTasksScreen(taskId = taskId, navController = navController)
        }

    }
}