package com.techquantum.tasksync.modules.tasks.addUpdate.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun DescriptionSection(description: String) {
    Column(modifier = Modifier.padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.LG)) {
        Text(
            text = stringResource(R.string.task_description),
            fontSize = AppDimension.FontSize.XL,
            fontWeight = FontWeight.Bold,
            color = ThemeColors.TextPrimary,
            modifier = Modifier.padding(bottom = AppDimension.Padding.SM)
        )
        Text(
            text = description,
            fontSize = AppDimension.FontSize.LG,
            fontWeight = FontWeight.Normal,
            color = ThemeColors.TextSecondary,
            lineHeight = 24.sp
        )
    }
}
