package com.techquantum.tasksync.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Centralized provider to return colors based on the current theme (dark / light)
object ColorProvider {
    @Composable
    fun background(): Color {
        return if (ThemeManager.isDarkTheme.value) Color(0xFF0F172A) else ThemeColors.BackgroundLight
    }

    @Composable
    fun surface(): Color {
        return if (ThemeManager.isDarkTheme.value) Color(0xFF0B1220) else ThemeColors.CardBackground
    }

    @Composable
    fun textPrimary(): Color {
        return if (ThemeManager.isDarkTheme.value) Color(0xFFE6EEF8) else ThemeColors.TextPrimary
    }

    @Composable
    fun textSecondary(): Color {
        return if (ThemeManager.isDarkTheme.value) Color(0xFF94A3B8) else ThemeColors.TextSecondary
    }

    @Composable
    fun primary(): Color {
        return if (ThemeManager.isDarkTheme.value) ThemeColors.Primary else ThemeColors.Primary
    }

    @Composable
    fun secondary(): Color {
        return if (ThemeManager.isDarkTheme.value) ThemeColors.Secondary else ThemeColors.Secondary
    }
}

