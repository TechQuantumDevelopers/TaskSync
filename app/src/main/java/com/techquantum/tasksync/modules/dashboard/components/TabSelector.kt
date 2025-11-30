package com.techquantum.tasksync.modules.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.R
import com.techquantum.tasksync.data.enums.Tab
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun TabSelector(
    selectedTab: Tab,
    onTabSelected: (Tab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.MD),
        horizontalArrangement = Arrangement.spacedBy(AppDimension.Padding.MD)
    ) {
        TabItem(
            label = stringResource(R.string.dashboard_tab_tasks),
            icon = Icons.Default.Check,
            isSelected = selectedTab == Tab.TASKS,
            onClick = { onTabSelected(Tab.TASKS) },
            modifier = Modifier.weight(1f)
        )
        
        TabItem(
            label = stringResource(R.string.dashboard_tab_notes),
            icon = Icons.Default.Edit,
            isSelected = selectedTab == Tab.NOTES,
            onClick = { onTabSelected(Tab.NOTES) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun TabItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(AppDimension.IconSize.FAB)
            .clip(RoundedCornerShape(AppDimension.Radius.LG))
            .background(if (isSelected) ThemeColors.Primary else ThemeColors.SurfaceDark)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) Color.White else ThemeColors.TextSecondary,
                modifier = Modifier.size(AppDimension.IconSize.SMALL)
            )
            
            Spacer(modifier = Modifier.width(AppDimension.Padding.SM))
            
            Text(
                text = label,
                color = if (isSelected) Color.White else ThemeColors.TextSecondary,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
            )
        }
    }
}
