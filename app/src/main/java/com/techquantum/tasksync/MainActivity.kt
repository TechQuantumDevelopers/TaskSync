package com.techquantum.tasksync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.techquantum.tasksync.navigation.AppNavigation
import com.techquantum.tasksync.ui.theme.TaskSyncTheme
import com.techquantum.tasksync.ui.theme.ThemeManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TaskSyncTheme(darkTheme = ThemeManager.isDarkTheme.value) {
                AppNavigation(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskSyncTheme(darkTheme = ThemeManager.isDarkTheme.value) {
        AppNavigation(rememberNavController())
    }
}