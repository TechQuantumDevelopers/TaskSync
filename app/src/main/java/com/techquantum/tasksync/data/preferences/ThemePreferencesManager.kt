package com.techquantum.tasksync.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ThemePreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFS_NAME = "theme_preferences"
        private const val KEY_IS_DARK_THEME = "is_dark_theme"
        private const val DEFAULT_DARK_THEME = true // Dark theme as default
    }

    var isDarkTheme by mutableStateOf(getDarkThemePreference())
        private set

    private fun getDarkThemePreference(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_DARK_THEME, DEFAULT_DARK_THEME)
    }

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme
        sharedPreferences.edit().putBoolean(KEY_IS_DARK_THEME, isDarkTheme).apply()
    }

    fun updateTheme(isDark: Boolean) {
        isDarkTheme = isDark
        sharedPreferences.edit().putBoolean(KEY_IS_DARK_THEME, isDark).apply()
    }
}
