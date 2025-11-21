package com.techquantum.tasksync.modules.tasks.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.ThemeColors
import com.techquantum.tasksync.ui.theme.ThemeToggle
import com.techquantum.tasksync.ui.theme.ThemeManager

@Composable
fun DashboardHeader(
    activeTasksCount: Int,
    onNotificationClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ThemeColors.BackgroundLight)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Greeting
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ThemeColors.TextLight,
                lineHeight = 32.sp
            )


            // Theme toggle and Notification Button
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ThemeToggle(isDark = ThemeManager.isDarkTheme.value, onToggle = { ThemeManager.toggle() })

                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = ThemeColors.TextLight,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Active tasks count
        Text(
            text = "You have $activeTasksCount active tasks.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = ThemeColors.TextSecondary,
            lineHeight = 24.sp
        )
    }
}