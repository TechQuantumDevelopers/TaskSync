package com.techquantum.tasksync.modules.tasks.addUpdate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun RemindersSection(reminder: String, onEditReminder: () -> Unit = {}) {
    Column(modifier = Modifier.padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.LG)) {
        Text(
            text = stringResource(R.string.task_reminders),
            fontSize = AppDimension.FontSize.XL,
            fontWeight = FontWeight.Bold,
            color = ThemeColors.TextPrimary,
            modifier = Modifier.padding(bottom = AppDimension.Padding.SM)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(AppDimension.Radius.SM))
                .background(ThemeColors.CardBackground)
                .padding(AppDimension.Padding.MD),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = stringResource(R.string.task_reminder),
                tint = ThemeColors.TextSecondary
            )
            Text(
                text = reminder,
                fontSize = AppDimension.FontSize.LG,
                color = ThemeColors.TextPrimary
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(R.string.common_edit),
                tint = ThemeColors.TextSecondary,
                modifier = Modifier.clickable {onEditReminder()}

            )
        }
    }
}

@Preview
@Composable
fun RemindersSectionPreview() {
    RemindersSection(reminder = "Tomorrow, 9:00 AM")
}
