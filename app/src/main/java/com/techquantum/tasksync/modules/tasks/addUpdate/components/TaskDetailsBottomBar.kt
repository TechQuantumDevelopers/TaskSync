package com.techquantum.tasksync.modules.tasks.addUpdate.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun TaskDetailsBottomBar(
    onCompleteTask: () -> Unit,
    onEditTask: () -> Unit,
    onArchiveTask: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = ThemeColors.BackgroundLight.copy(alpha = 0.8f),
        tonalElevation = AppDimension.Elevation.CARD
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimension.Padding.LG),
            horizontalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)
        ) {
            // Complete Task Button
            Button(
                onClick = onCompleteTask,
                modifier = Modifier
                    .weight(1f)
                    .height(AppDimension.IconSize.BUTTON_HEIGHT),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ThemeColors.Primary
                ),
                shape = RoundedCornerShape(AppDimension.Radius.MD)
            ) {
                Text(
                    text = stringResource(R.string.task_complete),
                    fontSize = AppDimension.FontSize.LG,
                    fontWeight = FontWeight.Bold
                )
            }

            // Edit Button
            IconButton(
                onClick = onEditTask,
                modifier = Modifier
                    .size(AppDimension.IconSize.LARGE)
                    .clip(RoundedCornerShape(AppDimension.Radius.MD))
                    .background(Color(0xFFE2E8F0))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.task_edit),
                    tint = ThemeColors.TextSecondary
                )
            }

            // Archive Button
            IconButton(
                onClick = onArchiveTask,
                modifier = Modifier
                    .size(AppDimension.IconSize.LARGE)
                    .clip(RoundedCornerShape(AppDimension.Radius.MD))
                    .background(Color(0xFFE2E8F0))
            ) {
                Icon(
                    imageVector = Icons.Default.Archive,
                    contentDescription = stringResource(R.string.common_archive),
                    tint = ThemeColors.TextSecondary
                )
            }
        }
    }
}
