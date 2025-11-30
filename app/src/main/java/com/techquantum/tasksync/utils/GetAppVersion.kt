package com.techquantum.tasksync.utils

import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getAppVersion(): String {
    val context = LocalContext.current
    return try {
        val packageInfo =
            context.packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName ?: "1.0.0"
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        "1.0.0"
    }
}