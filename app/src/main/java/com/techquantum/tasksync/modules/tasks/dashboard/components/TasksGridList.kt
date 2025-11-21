package com.techquantum.tasksync.modules.tasks.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.data.model.Task

@Composable
fun TasksGridList(
    tasks: List<Task>,
    onTaskMenuClick: (Task) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        tasks.chunked(2).forEach { rowTasks ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowTasks.forEach { task ->
                    Box(modifier = Modifier.weight(1f)) {
                        TaskCard(
                            task = task,
                            onMenuClick = { onTaskMenuClick(task) },
                            onClick = { onTaskMenuClick(task) }
                        )
                    }
                }
                // Add empty space if odd number of tasks
                if (rowTasks.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
