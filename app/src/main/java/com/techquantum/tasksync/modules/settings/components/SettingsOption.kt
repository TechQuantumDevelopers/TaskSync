package com.techquantum.tasksync.modules.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
 fun SettingsOption(
    icon: ImageVector,
    iconBackground: Color,
    iconTint: Color,
    title: String,
    description: String?,
    onClick: () -> Unit,
    showChevron: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.MD)
            .heightIn(min = AppDimension.Heights.SETTINGS_OPTION_ITEM - AppDimension.Padding.MD * 2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(AppDimension.Settings.ICON_BACKGROUND_SIZE)
                .clip(CircleShape)
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
            )
        }
        
        Spacer(modifier = Modifier.width(AppDimension.Padding.MD))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = ThemeColors.CardBackground
            )
            
            if (description != null) {
                Spacer(modifier = Modifier.height(AppDimension.Padding.XS))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = ThemeColors.TextSecondary
                )
            }
        }
        
        if (showChevron) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = ThemeColors.TextDisabled,
                modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
            )
        }
    }
}
