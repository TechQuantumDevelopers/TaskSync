package com.techquantum.tasksync.modules.tasks.addUpdate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.R
import com.techquantum.tasksync.data.model.Subtask
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun SubtasksSection(
    subtasks: List<Subtask>,
    onSubtaskToggle: (Subtask) -> Unit
) {
    val completedCount = subtasks.count { it.isCompleted == true }
    val totalCount = subtasks.size

    Column(modifier = Modifier.padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.LG)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppDimension.Padding.SM),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.task_subtasks),
                fontSize = AppDimension.FontSize.XL,
                fontWeight = FontWeight.Bold,
                color = ThemeColors.TextPrimary
            )
            Text(
                text = stringResource(R.string.task_completed_of_total, completedCount, totalCount),
                fontSize = AppDimension.FontSize.MD,
                fontWeight = FontWeight.Medium,
                color = ThemeColors.TextDisabled
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)
        ) {
            subtasks.forEach { subtask ->
                SubtaskItem(
                    subtask = subtask,
                    onToggle = { onSubtaskToggle(subtask) }
                )
            }
        }
    }
}
