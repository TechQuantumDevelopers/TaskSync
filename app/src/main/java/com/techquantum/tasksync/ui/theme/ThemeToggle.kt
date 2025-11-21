package com.techquantum.tasksync.ui.theme

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension

@Composable
fun ThemeToggle(
    isDark: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Animated scale for a subtle clickable effect
    val scale by animateFloatAsState(targetValue = if (isDark) 1.05f else 1f, animationSpec = tween(200))

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(AppDimension.Radius.LG))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onToggle() }
            .padding(AppDimension.Padding.SM),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)
    ) {
        // Sun / Moon icon (use material icons to avoid resource generation issues)
        Box(
            modifier = Modifier
                .size(AppDimension.IconSize.LARGE)
                .scale(scale),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (!isDark) Icons.Filled.WbSunny else Icons.Filled.NightsStay,
                contentDescription = if (!isDark) stringResource(R.string.theme_light_label) else stringResource(R.string.theme_dark_label),
                modifier = Modifier.size(AppDimension.IconSize.SMALL)
            )
        }

        Text(
            text = if (isDark) stringResource(R.string.theme_dark) else stringResource(R.string.theme_light),
            fontSize = AppDimension.FontSize.SM,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.size(AppDimension.Padding.XS))
    }
}
