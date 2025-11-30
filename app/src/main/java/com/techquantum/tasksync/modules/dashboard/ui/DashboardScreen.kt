package com.techquantum.tasksync.modules.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension

@Composable
fun DashboardScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.dashboard_today),
                fontSize = AppDimension.FontSize.XXL,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Dashboard - Coming Soon",
                fontSize = AppDimension.FontSize.LG,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = AppDimension.Padding.MD)
            )
        }
    }
}
