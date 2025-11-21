package com.techquantum.tasksync.modules.tasks.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.data.model.Project
import com.techquantum.tasksync.ui.theme.ThemeColors
import com.techquantum.tasksync.ui.theme.AppDimension

@Composable
fun ProjectItem(
    project: Project,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)
    ) {
        Surface(
            onClick = onClick,
            modifier = Modifier.size(AppDimension.IconSize.FAB),
            shape = RoundedCornerShape(AppDimension.Radius.MD),
            color = if (isSelected) ThemeColors.Primary else ThemeColors.Secondary.copy(alpha = 0.15f),
            tonalElevation = if (isSelected) AppDimension.Elevation.CARD else 0.dp
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = project.icon,
                    contentDescription = project.name,
                    tint = if (isSelected) Color.White else ThemeColors.TextPrimary,
                    modifier = Modifier.size(AppDimension.IconSize.XLARGE)
                )
            }
        }

        Text(
            text = project.name.orEmpty(),
            fontSize = AppDimension.FontSize.MD,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
            color = if (isSelected) Color.White else ThemeColors.TextSecondary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            lineHeight = AppDimension.FontSize.MD
        )
    }
}