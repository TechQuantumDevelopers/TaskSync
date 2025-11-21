package com.techquantum.tasksync.ui.theme

import androidx.compose.runtime.mutableStateOf

/**
 * Simple in-memory theme manager. Keeps a mutable state that Compose can observe.
 * Replace with persistent storage (DataStore) if you want to persist the choice across restarts.
 */
object ThemeManager {
    // default to system (false) — you can set true to start dark
    val isDarkTheme = mutableStateOf(false)

    fun toggle() {
        isDarkTheme.value = !isDarkTheme.value
    }

    fun setDark(isDark: Boolean) {
        isDarkTheme.value = isDark
    }
}

