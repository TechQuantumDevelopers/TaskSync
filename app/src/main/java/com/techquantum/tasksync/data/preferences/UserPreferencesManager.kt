package com.techquantum.tasksync.data.preferences

import android.content.Context
import android.content.SharedPreferences

class UserPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFS_NAME = "task_sync_preferences"
        private const val KEY_IS_FIRST_TIME = "is_first_time"
    }

    fun isFirstTimeUser(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_FIRST_TIME, true)
    }

    fun setOnboardingCompleted() {
        sharedPreferences.edit().putBoolean(KEY_IS_FIRST_TIME, false).apply()
    }
}
