package com.techquantum.tasksync.data.enums

import androidx.compose.ui.graphics.Color

enum class TaskPriority(val label: String, val bgColor: Color, val textColor: Color) {
    HIGH("High Priority", Color(0xFFFEE2E2), Color(0xFFB91C1C)),
    MEDIUM("Medium Priority", Color(0xFFFEF3C7), Color(0xFFB45309)),
    LOW("Low Priority", Color(0xFFD1FAE5), Color(0xFF065F46))
}
