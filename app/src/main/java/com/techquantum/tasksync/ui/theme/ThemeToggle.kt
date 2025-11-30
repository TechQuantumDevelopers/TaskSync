package com.techquantum.tasksync.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.R

@Composable
fun ThemeToggle(
    isDark: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Animation specs
    val toggleWidth = 120.dp
    val indicatorSize = 36.dp

    // Animated properties with spring animation for smooth, bouncy effect
    val indicatorOffset by animateDpAsState(
        targetValue = if (isDark) toggleWidth - indicatorSize - 4.dp else 4.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "indicator_offset"
    )

    // Icon rotation animation
    val iconRotation by animateFloatAsState(
        targetValue = if (isDark) 360f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "icon_rotation"
    )

    // Icon scale animation for emphasis on toggle
    val iconScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "icon_scale"
    )

    // Background color animation
    val backgroundColor by animateColorAsState(
        targetValue = if (isDark)
            Color(0xFF1E1E2E)
        else
            Color(0xFFE8F4FF),
        animationSpec = tween(durationMillis = 400),
        label = "background_color"
    )

    // Indicator color animation
    val indicatorColor by animateColorAsState(
        targetValue = if (isDark)
            Color(0xFF7C3AED)
        else
            Color(0xFFFCD34D),
        animationSpec = tween(durationMillis = 400),
        label = "indicator_color"
    )

    // Icon color animation
    val iconColor by animateColorAsState(
        targetValue = if (isDark)
            Color(0xFFFFFFFF)
        else
            Color(0xFF1E3A8A),
        animationSpec = tween(durationMillis = 400),
        label = "icon_color"
    )

    Box(
        modifier = modifier
            .width(toggleWidth)
            .clip(RoundedCornerShape(AppDimension.Radius.LG))
            .background(backgroundColor)
            .clickable { onToggle() }
            .padding(4.dp)
    ) {
        // Sliding indicator background
        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .size(indicatorSize)
                .clip(CircleShape)
                .background(indicatorColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isDark) Icons.Filled.NightsStay else Icons.Filled.WbSunny,
                contentDescription = if (isDark)
                    stringResource(R.string.theme_dark_label)
                else
                    stringResource(R.string.theme_light_label),
                tint = iconColor,
                modifier = Modifier
                    .size(AppDimension.IconSize.SMALL)
                    .rotate(iconRotation)
                    .scale(iconScale)
            )
        }

        // Static labels for context
        Row(
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = AppDimension.Padding.SM),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sun icon placeholder (hidden, just for spacing)
            Spacer(modifier = Modifier.size(AppDimension.IconSize.SMALL))

            // Moon icon placeholder (hidden, just for spacing)
            Spacer(modifier = Modifier.size(AppDimension.IconSize.SMALL))
        }
    }
}

@Preview
@Composable
fun ThemeTogglePreview() {
    ThemeToggle(isDark = false, onToggle = {})
}