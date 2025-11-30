package com.techquantum.tasksync.ui.theme

import androidx.compose.ui.graphics.Color

object ThemeColors {

    // Brand Colors
    val Primary = Color(0xFF3E62F5)        // Royal Blue
    val Secondary = Color(0xFF00B7C2)      // Aqua Cyan
    val Accent = Color(0xFFFF6B5A)         // Coral Red (CTA / Alerts)

    // Supporting Colors
    val Success = Color(0xFF4CCB72)        // Soft Green
    val Warning = Color(0xFFF6C445)        // Warm Amber
    val Danger = Color(0xFFEF4444)         // Danger Red

    // Settings Icon Backgrounds
    val IconBackgroundGreen = Color(0xFF4CCB72).copy(alpha = 0.15f)
    val IconBackgroundOrange = Color(0xFFFF8C42).copy(alpha = 0.15f)
    val IconBackgroundBlue = Color(0xFF3E62F5).copy(alpha = 0.15f)

    // FAB Gradient
    val FabGradientStart = Color(0xFF6B5FED)  // Purple
    val FabGradientEnd = Color(0xFF3E62F5)    // Blue

    // Vertical Line for Subtasks
    val SubtaskConnectorLine = Color(0xFF4B5563)

    // Light Mode Backgrounds
    val BackgroundLight = Color(0xFFF7F9FC)      // Soft Off-White
    val CardBackground = Color(0xFFFFFFFF)       // White
    val CardBorder = Color(0xFFE7ECF3)           // Soft Divider Border

    // Text Colors
    val TextPrimary = Color(0xFF1B1F24)          // Dark Charcoal
    val TextSecondary = Color(0xFF4B5563)        // Cool Gray
    val TextDisabled = Color(0xFF9CA3AF)         // Muted Gray
    val TextLight = Color(0xFF333333)            // General light text

    // Dark Mode Backgrounds
    val BackgroundDark = Color(0xFF0E141B)       // Deep Blue-Black
    val SurfaceDark = Color(0xFF1C242E)          // Elevated surface / Cards
    val CardDark = Color(0xFF2A333F)             // Inputs / list item background

    val NoteOrange = Color(0xFFFFA726)

    val NotePink = Color(0xFFFF80AB)

    val NoteBlue = Color(0xFF42A5F5)

    val NoteGreen = Color(0xFF66BB6A)

    // Legacy (Keeping or can remove if unused)
    val Purple80 = Color(0xFFD0BCFF)
    val PurpleGrey80 = Color(0xFFCCC2DC)
    val Pink80 = Color(0xFFEFB8C8)

    val Purple40 = Color(0xFF6650A4)
    val PurpleGrey40 = Color(0xFF625B71)
    val Pink40 = Color(0xFF7D5260)

}
