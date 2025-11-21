package com.techquantum.tasksync.modules.tasks.addUpdate.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.techquantum.tasksync.data.enums.TaskPriority
import com.techquantum.tasksync.data.model.Subtask
import com.techquantum.tasksync.data.model.TaskDetail
import com.techquantum.tasksync.ui.theme.ThemeColors

// Default Data
fun getDefaultTask() = TaskDetail(
    id = "1",
    title = "Finalize Q4 Marketing Strategy",
    projectName = "Project Phoenix",
    priority = TaskPriority.HIGH,
    dueDate = "Tomorrow",
    category = "Design",
    quarter = "Q4",
    description = "Review the latest analytics report, update the presentation deck with new findings, and prepare a summary for the leadership meeting. Focus on social media engagement metrics and proposed budget adjustments.",
    subtasks = listOf(
        Subtask("1", "Review analytics report", true),
        Subtask("2", "Draft initial summary", true),
        Subtask("3", "Update presentation deck", true),
        Subtask("4", "Finalize budget adjustments", false),
        Subtask("5", "Send summary for pre-read", false)
    ),
    reminder = "1 hour before due"
)

// Add/Edit Task Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUpdateTasksScreen(
    taskId: String? = null,
    onBackClick: () -> Unit = {},
    onSaveTask: (TaskDetail) -> Unit = {},
    navController: NavHostController
) {
    // Fetch/load the task by id. Replace this with real repository/ViewModel lookup.
    val task = remember(taskId) {
        if (taskId.isNullOrEmpty()) null else getDefaultTask().copy(id = taskId)
    }

    var title by remember { mutableStateOf(task?.title ?: "") }
    var projectName by remember { mutableStateOf(task?.projectName ?: "") }
    var selectedPriority by remember { mutableStateOf(task?.priority ?: TaskPriority.MEDIUM) }
    var dueDate by remember { mutableStateOf(task?.dueDate ?: "") }
    var category by remember { mutableStateOf(task?.category ?: "") }
    var quarter by remember { mutableStateOf(task?.quarter ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var reminder by remember { mutableStateOf(task?.reminder ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (task == null) "Add Task" else "Edit Task",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            val newTask = TaskDetail(
                                id = task?.id ?: System.currentTimeMillis().toString(),
                                title = title,
                                projectName = projectName,
                                priority = selectedPriority,
                                dueDate = dueDate,
                                category = category,
                                quarter = quarter,
                                description = description,
                                subtasks = task?.subtasks ?: emptyList(),
                                reminder = reminder
                            )
                            onSaveTask(newTask)
                        },
                        enabled = title.isNotBlank() && projectName.isNotBlank()
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (title.isNotBlank() && projectName.isNotBlank())
                                ThemeColors.Primary
                            else ThemeColors.TextDisabled
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ThemeColors.BackgroundLight
                )
            )
        },
        containerColor = ThemeColors.BackgroundLight
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Task Title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Task Title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                minLines = 2,
                maxLines = 3
            )

            // Project Name
            OutlinedTextField(
                value = projectName,
                onValueChange = { projectName = it },
                label = { Text("Project Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Priority Selection
            Text(
                text = "Priority",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = ThemeColors.TextPrimary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TaskPriority.entries.forEach { priority ->
                    FilterChip(
                        selected = selectedPriority == priority,
                        onClick = { selectedPriority = priority },
                        label = { Text(priority.label) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = priority.bgColor,
                            selectedLabelColor = priority.textColor
                        )
                    )
                }
            }

            // Due Date
            OutlinedTextField(
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("Due Date") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select Date"
                    )
                }
            )

            // Category
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Quarter
            OutlinedTextField(
                value = quarter,
                onValueChange = { quarter = it },
                label = { Text("Quarter") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp),
                singleLine = false,
                minLines = 4,
                maxLines = 8
            )

            // Reminder
            OutlinedTextField(
                value = reminder,
                onValueChange = { reminder = it },
                label = { Text("Reminder") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Reminder"
                    )
                }
            )
        }
    }
}