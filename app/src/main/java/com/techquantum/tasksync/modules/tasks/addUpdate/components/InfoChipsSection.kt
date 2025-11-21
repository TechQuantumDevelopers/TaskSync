package com.techquantum.tasksync.modules.tasks.addUpdate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.data.enums.TaskPriority

@Composable
fun InfoChipsSection(
    priority: TaskPriority,
    dueDate: String,
    category: String,
    quarter: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Priority Chip
        InfoChip(
            label = priority.label,
            backgroundColor = priority.bgColor,
            textColor = priority.textColor
        )

        // Due Date Chip
        InfoChip(
            label = "Due: $dueDate",
            backgroundColor = Color(0xFFFEF3C7),
            textColor = Color(0xFFB45309)
        )

        // Category Chip
        InfoChip(
            label = category,
            backgroundColor = Color(0xFFE0E7FF),
            textColor = Color(0xFF4338CA)
        )

        // Quarter Chip
        InfoChip(
            label = quarter,
            backgroundColor = Color(0xFFCCFBF1),
            textColor = Color(0xFF115E59)
        )
    }
}
