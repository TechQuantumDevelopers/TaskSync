package com.techquantum.tasksync.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Project(
    val id: String? = "",
    val name: String? = "",
    val icon: ImageVector,
    val tint: Color = Color.Unspecified
)
