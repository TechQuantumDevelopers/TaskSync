package com.techquantum.tasksync.modules.tasks.addUpdate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techquantum.tasksync.data.model.Subtask
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun SubtaskItem(
    subtask: Subtask,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(ThemeColors.CardBackground)
            .clickable(onClick = onToggle)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Checkbox
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    if (subtask.isCompleted == true) ThemeColors.CardBackground
                    else ThemeColors.CardBackground
                )
                .border(
                    width = 2.dp,
                    color = if (subtask.isCompleted == true) ThemeColors.TextDisabled
                    else ThemeColors.Primary,
                    shape = RoundedCornerShape(4.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (subtask.isCompleted == true) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = ThemeColors.TextDisabled,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // Subtask Text
        Text(
            text = subtask.title.orEmpty(),
            fontSize = 16.sp,
            color = if (subtask.isCompleted == true) ThemeColors.TextDisabled
            else ThemeColors.TextPrimary,
            textDecoration = if (subtask.isCompleted == true) TextDecoration.LineThrough
            else TextDecoration.None,
            modifier = Modifier.weight(1f)
        )
    }
}
