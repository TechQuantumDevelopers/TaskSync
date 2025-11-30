package com.techquantum.tasksync.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.techquantum.tasksync.ui.theme.ThemeColors.Pink40
import com.techquantum.tasksync.ui.theme.ThemeColors.Pink80
import com.techquantum.tasksync.ui.theme.ThemeColors.Purple40
import com.techquantum.tasksync.ui.theme.ThemeColors.Purple80
import com.techquantum.tasksync.ui.theme.ThemeColors.PurpleGrey40
import com.techquantum.tasksync.ui.theme.ThemeColors.PurpleGrey80

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TaskSyncTheme(
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}