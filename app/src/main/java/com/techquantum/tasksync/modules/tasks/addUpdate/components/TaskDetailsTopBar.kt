package com.techquantum.tasksync.modules.tasks.addUpdate.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.techquantum.tasksync.ui.theme.ThemeColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsTopBar(
    projectName: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = projectName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ThemeColors.TextPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = ThemeColors.TextSecondary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ThemeColors.BackgroundLight.copy(alpha = 0.8f)
        )
    )
}