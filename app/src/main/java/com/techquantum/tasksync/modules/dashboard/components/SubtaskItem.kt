package com.techquantum.tasksync.modules.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.techquantum.tasksync.data.model.Subtask
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun SubtaskItem(
    subtask: Subtask,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = AppDimension.Padding.XS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Vertical Connecting Line
        Canvas(
            modifier = Modifier
                .width(AppDimension.Dashboard.SUBTASK_LINE_START_OFFSET)
                .height(AppDimension.IconSize.MEDIUM + AppDimension.Padding.SM)
        ) {
            drawLine(
                color = ThemeColors.SubtaskConnectorLine,
                start = Offset(AppDimension.Dashboard.SUBTASK_LINE_START_OFFSET.toPx(), 0f),
                end = Offset(AppDimension.Dashboard.SUBTASK_LINE_START_OFFSET.toPx(), size.height),
                strokeWidth = AppDimension.Dashboard.SUBTASK_LINE_WIDTH.toPx()
            )
        }
        
        // Checkbox
        Checkbox(
            checked = subtask.isCompleted,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = ThemeColors.Primary,
                uncheckedColor = ThemeColors.TextDisabled,
                checkmarkColor = ThemeColors.CardBackground
            )
        )
        
        Spacer(modifier = Modifier.width(AppDimension.Padding.MD))
        
        // Subtask Title
        Text(
            text = subtask.title,
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = if (subtask.isCompleted) ThemeColors.TextDisabled else ThemeColors.TextSecondary,
            textDecoration = if (subtask.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
            fontWeight = if (subtask.isCompleted) FontWeight.Normal else FontWeight.Normal,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
