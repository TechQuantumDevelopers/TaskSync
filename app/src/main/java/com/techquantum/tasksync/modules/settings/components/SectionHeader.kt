package com.techquantum.tasksync.modules.settings.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
 fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        color = ThemeColors.TextSecondary,
        modifier = Modifier.padding(
            start = AppDimension.Padding.XS,
            bottom = AppDimension.Padding.MD
        )
    )
}