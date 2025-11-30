package com.techquantum.tasksync.modules.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.MD),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = ThemeColors.Primary,
            modifier = Modifier.size(AppDimension.IconSize.LARGE)
        )
        
        Spacer(modifier = Modifier.width(AppDimension.Padding.SM))
        
        Text(
            text = stringResource(R.string.dashboard_app_title),
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = ThemeColors.CardBackground
        )
    }
}
