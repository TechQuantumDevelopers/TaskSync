package com.techquantum.tasksync.modules.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun SettingsCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(AppDimension.Radius.LG),
        colors = CardDefaults.cardColors(
            containerColor = ThemeColors.SurfaceDark
        )
    ) {
        Column(
            modifier = Modifier.padding(vertical = AppDimension.Padding.XS)
        ) {
            content()
        }
    }
}
