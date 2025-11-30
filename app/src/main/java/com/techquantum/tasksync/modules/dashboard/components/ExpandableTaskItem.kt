package com.techquantum.tasksync.modules.dashboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.techquantum.tasksync.R
import com.techquantum.tasksync.data.model.Subtask
import com.techquantum.tasksync.data.model.Task
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun ExpandableTaskItem(
    task: Task,
    onTaskCheckedChange: (Boolean) -> Unit = {},
    onSubtaskCheckedChange: (Subtask, Boolean) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "chevron_rotation"
    )
    
    val completedSubtasks = task.subtasks.count { it.isCompleted }
    val totalSubtasks = task.subtasks.size
    
    Column(modifier = modifier.fillMaxWidth()) {
        // Parent Task Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = AppDimension.Dashboard.TASK_ITEM_MIN_HEIGHT)
                .clickable { if (task.subtasks.isNotEmpty()) isExpanded = !isExpanded }
                .padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.SM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onTaskCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = ThemeColors.Primary,
                    uncheckedColor = ThemeColors.TextDisabled,
                    checkmarkColor = ThemeColors.CardBackground
                )
            )
            
            Spacer(modifier = Modifier.width(AppDimension.Padding.MD))
            
            // Task Title
            Text(
                text = task.title,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = if (task.isCompleted) ThemeColors.TextDisabled else ThemeColors.CardBackground,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                fontWeight = if (task.isCompleted) FontWeight.Normal else FontWeight.Medium,
                modifier = Modifier.weight(1f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            // Progress Indicator
            if (totalSubtasks > 0) {
                Spacer(modifier = Modifier.width(AppDimension.Padding.MD))
                Text(
                    text = stringResource(R.string.dashboard_task_progress, completedSubtasks, totalSubtasks),
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    color = ThemeColors.TextSecondary,
                    fontWeight = FontWeight.Medium
                )
            }
            
            // Chevron Icon
            if (task.subtasks.isNotEmpty()) {
                Spacer(modifier = Modifier.width(AppDimension.Padding.SM))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) 
                        stringResource(R.string.dashboard_collapse_cd) 
                    else 
                        stringResource(R.string.dashboard_expand_cd),
                    tint = ThemeColors.TextDisabled,
                    modifier = Modifier
                        .size(AppDimension.IconSize.MEDIUM)
                        .rotate(rotationAngle)
                )
            }
        }
        
        // Subtasks (when expanded)
        if (isExpanded && task.subtasks.isNotEmpty()) {
            Column(modifier = Modifier.padding(start = AppDimension.Dashboard.SUBTASK_INDENT)) {
                task.subtasks.forEach { subtask ->
                    SubtaskItem(
                        subtask = subtask,
                        onCheckedChange = { checked ->
                            onSubtaskCheckedChange(subtask, checked)
                        }
                    )
                }
            }
        }
    }
}
