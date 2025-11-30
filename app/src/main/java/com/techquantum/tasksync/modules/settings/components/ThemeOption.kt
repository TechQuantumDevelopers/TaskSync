package com.techquantum.tasksync.modules.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
 fun ThemeOption(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(AppDimension.Radius.MD),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) ThemeColors.Primary else ThemeColors.CardDark
        ),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(
                width = AppDimension.Padding.XS / 2,
                color = ThemeColors.Primary
            )
        } else null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimension.Padding.LG),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) Color.White else ThemeColors.TextSecondary,
                modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
            )
            
            Spacer(modifier = Modifier.height(AppDimension.Padding.SM))
            
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color = if (isSelected) Color.White else ThemeColors.TextSecondary
            )
        }
    }
}
