package com.techquantum.tasksync.modules.tasks.addUpdate.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techquantum.tasksync.data.model.Subtask
import com.techquantum.tasksync.data.model.TaskDetail
import com.techquantum.tasksync.modules.tasks.addUpdate.ui.getDefaultTask
import com.techquantum.tasksync.ui.theme.ThemeColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    paddingValues: PaddingValues,
    task: TaskDetail = getDefaultTask(),
    onBackClick: () -> Unit = {},
    onCompleteTask: () -> Unit = {},
    onEditTask: () -> Unit = {},
    onArchiveTask: () -> Unit = {},
    onSubtaskToggle: (Subtask) -> Unit = {}
) {
    var subtasks by remember { mutableStateOf(task.subtasks) }

    Scaffold(
        topBar = {
            TaskDetailsTopBar(
                projectName = task.projectName,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            TaskDetailsBottomBar(
                onCompleteTask = onCompleteTask,
                onEditTask = onEditTask,
                onArchiveTask = onArchiveTask
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                // Task Title
                Text(
                    text = task.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = ThemeColors.TextPrimary,
                    lineHeight = 36.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                )

                // Info Chips
                InfoChipsSection(
                    priority = task.priority,
                    dueDate = task.dueDate,
                    category = task.category,
                    quarter = task.quarter
                )

                // Description Section
                DescriptionSection(description = task.description)

                // Subtasks Section
                SubtasksSection(
                    subtasks = subtasks,
                    onSubtaskToggle = { subtask ->
                        subtasks = subtasks.map {
                            if (it.id == subtask.id) it.copy(isCompleted = it.isCompleted?.not())
                            else it
                        }
                        onSubtaskToggle(subtask)
                    }
                )

                // Reminders Section
                RemindersSection(
                    reminder = task.reminder,
                    onEditReminder = {
                        /* Edit reminder by time picker*/
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        containerColor = ThemeColors.BackgroundLight
    )
}

@Composable
@Preview
fun TaskDetailsScreenPreview() {
    TaskDetailsScreen(PaddingValues())
}