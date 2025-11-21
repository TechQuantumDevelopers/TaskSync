package com.techquantum.tasksync.modules.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techquantum.tasksync.modules.splash.components.PulsingDots
import com.techquantum.tasksync.modules.splash.navigation.SplashRoute
import com.techquantum.tasksync.modules.tasks.dashboard.navigation.DashboardGraphs
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo circle with app initials
            Surface(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape),
                shape = CircleShape,
                color = circleColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "TS",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Custom loader: staggered pulsing dots
            PulsingDots()
        }
    }

    // Auto-navigate / finish splash after duration
    LaunchedEffect(Unit) {
        delay(1800)
        navController.navigate(DashboardGraphs.Dashboard.route) {
            popUpTo(SplashRoute.Splash.route) { inclusive = true }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}