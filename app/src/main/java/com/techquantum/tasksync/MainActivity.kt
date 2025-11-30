package com.techquantum.tasksync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.techquantum.tasksync.navigation.AppNavigation
import com.techquantum.tasksync.ui.theme.TaskSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TaskSyncTheme {
                AppNavigation(navController = navController)
            }
        }
    }
}

