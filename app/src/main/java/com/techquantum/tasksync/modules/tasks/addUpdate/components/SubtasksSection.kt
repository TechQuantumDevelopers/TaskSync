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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techquantum.tasksync.data.model.Subtask
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun SubtasksSection(
    subtasks: List<Subtask>,
    onSubtaskToggle: (Subtask) -> Unit
) {
    val completedCount = subtasks.count { it.isCompleted == true }
    val totalCount = subtasks.size

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Subtasks",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ThemeColors.TextPrimary
            )
            Text(
                text = "$completedCount of $totalCount completed",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = ThemeColors.TextDisabled
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
