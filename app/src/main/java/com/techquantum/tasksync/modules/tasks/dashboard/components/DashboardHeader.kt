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
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors
import com.techquantum.tasksync.ui.theme.ThemeToggle
import com.techquantum.tasksync.ui.theme.ThemeManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DashboardHeader(
    activeTasksCount: Int,
    onNotificationClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ThemeColors.BackgroundLight)
            .padding(AppDimension.Padding.LG)
    ) {
        Spacer(modifier = Modifier.height(AppDimension.Padding.SM))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Greeting
            Text(
                text = stringResource(R.string.app_name),
                fontSize = AppDimension.FontSize.XXL,
                fontWeight = FontWeight.Bold,
                color = ThemeColors.TextLight,
                lineHeight = 32.sp
            )


            // Theme toggle and Notification Button
            Row(horizontalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)) {
                ThemeToggle(isDark = ThemeManager.isDarkTheme.value, onToggle = { ThemeManager.toggle() })

                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = stringResource(R.string.common_notifications_cd),
                        tint = ThemeColors.TextLight,
                        modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(AppDimension.Padding.XS))

        // Active tasks count
        Text(
            text = stringResource(R.string.dashboard_you_have_active_tasks, activeTasksCount),
            fontSize = AppDimension.FontSize.LG,
            fontWeight = FontWeight.Normal,
            color = ThemeColors.TextSecondary,
            lineHeight = 24.sp
        )
    }
}