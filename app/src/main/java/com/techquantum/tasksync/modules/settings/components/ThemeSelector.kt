package com.techquantum.tasksync.modules.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.R
import com.techquantum.tasksync.modules.settings.ui.ThemeMode
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
 fun ThemeSelector(
    selectedTheme: ThemeMode,
    onThemeSelected: (ThemeMode) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppDimension.Padding.LG)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = AppDimension.Padding.LG)
        ) {
            Box(
                modifier = Modifier
                    .size(AppDimension.Settings.ICON_BACKGROUND_SIZE)
                    .clip(CircleShape)
                    .background(ThemeColors.IconBackgroundBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Brightness6,
                    contentDescription = stringResource(R.string.settings_theme_icon_cd),
                    tint = ThemeColors.Primary,
                    modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
                )
            }
            
            Spacer(modifier = Modifier.width(AppDimension.Padding.MD))
            
            Text(
                text = stringResource(R.string.settings_theme_label),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = ThemeColors.CardBackground
            )
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppDimension.Padding.MD)
        ) {
            ThemeOption(
                icon = Icons.Default.LightMode,
                label = stringResource(R.string.settings_theme_light),
                isSelected = selectedTheme == ThemeMode.LIGHT,
                onClick = { onThemeSelected(ThemeMode.LIGHT) },
                modifier = Modifier.weight(1f)
            )
            
            ThemeOption(
                icon = Icons.Default.DarkMode,
                label = stringResource(R.string.settings_theme_dark),
                isSelected = selectedTheme == ThemeMode.DARK,
                onClick = { onThemeSelected(ThemeMode.DARK) },
                modifier = Modifier.weight(1f)
            )
            
            ThemeOption(
                icon = Icons.Default.PhoneAndroid,
                label = stringResource(R.string.settings_theme_system),
                isSelected = selectedTheme == ThemeMode.SYSTEM,
                onClick = { onThemeSelected(ThemeMode.SYSTEM) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
