package com.techquantum.tasksync.modules.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techquantum.tasksync.R
import com.techquantum.tasksync.data.preferences.UserPreferencesManager
import com.techquantum.tasksync.modules.dashboard.navigation.DashboardRoute
import com.techquantum.tasksync.modules.onboarding.navigation.OnboardingRoute
import com.techquantum.tasksync.modules.splash.components.PulsingDots
import com.techquantum.tasksync.modules.splash.navigation.SplashRoute
import com.techquantum.tasksync.ui.theme.AppDimension
import androidx.compose.foundation.shape.CircleShape
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
                modifier = Modifier,
                shape = CircleShape,
                color = circleColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(R.string.splash_ts_initials),
                        fontSize = AppDimension.FontSize.LOGO,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppDimension.Padding.XL))

            // Custom loader: staggered pulsing dots
            PulsingDots()
        }
    }

    // Auto-navigate / finish splash after duration
    LaunchedEffect(Unit) {
        delay(1800)
        val context = navController.context
        val preferencesManager = UserPreferencesManager(context)
        
        if (preferencesManager.isFirstTimeUser()) {
            navController.navigate(OnboardingRoute.Onboarding.route) {
                popUpTo(SplashRoute.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(DashboardRoute.Dashboard.route) {
                popUpTo(SplashRoute.Splash.route) { inclusive = true }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}