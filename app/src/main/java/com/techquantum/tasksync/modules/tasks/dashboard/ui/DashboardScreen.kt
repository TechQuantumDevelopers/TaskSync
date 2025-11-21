package com.techquantum.tasksync.modules.tasks.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ColorProvider
import com.techquantum.tasksync.ui.theme.ThemeColors
import com.techquantum.tasksync.data.enums.TaskPriority
import com.techquantum.tasksync.data.model.Project
import com.techquantum.tasksync.data.model.Task
import com.techquantum.tasksync.modules.tasks.addUpdate.navigation.TasksGraphs
import com.techquantum.tasksync.modules.tasks.dashboard.components.DashboardHeader
import com.techquantum.tasksync.modules.tasks.dashboard.components.ProjectsSection
import com.techquantum.tasksync.modules.tasks.dashboard.components.TasksGridList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Surface
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DashboardScreen(
    navController: NavHostController
) {
    val activeTasksCount = 0
    val projectsState = remember { mutableStateListOf<Project>().apply { addAll(getDefaultProjects()) } }
    val todayTasks = getDefaultTodayTasks()
    val upcomingTasks = getDefaultUpcomingTasks()

    var selectedProject by remember { mutableStateOf(projectsState.firstOrNull()) }

    var showAddProjectSheet by remember { mutableStateOf(false) }
    var showFabMenu by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(ColorProvider.background())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Section
                DashboardHeader(
                    activeTasksCount = activeTasksCount,
                    onNotificationClick = { navController.navigate(com.techquantum.tasksync.modules.notifications.navigation.NotificationsRoute.Notifications.route) }
                )


                // Projects Section
                ProjectsSection(
                    projects = projectsState,
                    selectedProject = selectedProject,
                    onProjectClick = { project ->
                        selectedProject = project
                        navController.navigate(TasksGraphs.Tasks.withId(project.id))
                    },
                    onAddProject = { showAddProjectSheet = true }
                )

                // Today Section
                Text(
                    text = stringResource(R.string.dashboard_today),
                    fontSize = AppDimension.FontSize.XL,
                    fontWeight = FontWeight.Bold,
                    color = ColorProvider.textPrimary(),
                    modifier = Modifier.padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.MD)
                )

                val onTaskClick: (Task) -> Unit = { task ->
                    // navigate to details/edit screen for the selected task
                    navController.navigate(TasksGraphs.Tasks.withId(task.id))
                }

                TasksGridList(
                    tasks = todayTasks,
                    onTaskMenuClick = onTaskClick
                )

                // Upcoming Section
                Text(
                    text = stringResource(R.string.dashboard_upcoming),
                    fontSize = AppDimension.FontSize.XL,
                    fontWeight = FontWeight.Bold,
                    color = ColorProvider.textPrimary(),
                    modifier = Modifier.padding(
                        start = AppDimension.Padding.LG,
                        end = AppDimension.Padding.LG,
                        top = AppDimension.Padding.XL,
                        bottom = AppDimension.Padding.SM
                    )
                )

                TasksGridList(
                    tasks = upcomingTasks,
                    onTaskMenuClick = onTaskClick
                )

                Spacer(modifier = Modifier.height(AppDimension.Spacing.SECTION_BOTTOM))
            }

            // Floating Action Button
            FloatingActionButton(
                onClick = { showFabMenu = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(AppDimension.Padding.XL)
                    .size(AppDimension.IconSize.FAB)
                    .shadow(AppDimension.Elevation.CARD, CircleShape),
                containerColor = ThemeColors.Primary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.common_add_task_cd),
                    modifier = Modifier.size(AppDimension.IconSize.XLARGE)
                )
            }

            // FAB menu (simple AlertDialog for options)
            if (showFabMenu) {
                AlertDialog(
                    onDismissRequest = { showFabMenu = false },
                    title = { Text(text = "Add") },
                    text = {
                        Column {
                            TextButton(onClick = {
                                showFabMenu = false
                                // navigate to add upcoming tasks (re-use add task screen)
                                navController.navigate(TasksGraphs.Tasks().route)
                            }) { Text(text = "Add Upcoming Task") }
                            TextButton(onClick = {
                                showFabMenu = false
                                navController.navigate(TasksGraphs.Tasks().route)
                            }) { Text(text = "Add Task") }
                        }
                    },
                    confirmButton = {}
                )
            }

            // Add Project sheet (simple modal dialog used as bottom sheet)
            if (showAddProjectSheet) {
                AddProjectBottomSheet(
                    onDismiss = { showAddProjectSheet = false },
                    onSave = { name, icon, tint ->
                        // add new project to state
                        val newProject = Project(id = System.currentTimeMillis().toString(), name = name, icon = icon, tint = tint)
                        projectsState.add(newProject)
                        selectedProject = newProject
                        showAddProjectSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun AddProjectBottomSheet(
    onDismiss: () -> Unit,
    onSave: (String, androidx.compose.ui.graphics.vector.ImageVector, Color) -> Unit
) {
    // small sheet simulated with AlertDialog to avoid experimental sheet API usage
    var name by remember { mutableStateOf("") }
    var selectedIconIndex by remember { mutableStateOf(0) }
    var selectedColor by remember { mutableStateOf(ThemeColors.Secondary) }
    val icons = listOf(Icons.Default.Star, Icons.Default.Build, Icons.Default.Phone, Icons.Default.Add)
    val colors = listOf(ThemeColors.Primary, ThemeColors.Secondary, Color(0xFFE2E8F0), Color(0xFFFFD700))

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Add Project") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text(text = "Project name") })
                Spacer(modifier = Modifier.height(AppDimension.Padding.SM))
                Text(text = "Choose icon")
                Row(horizontalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)) {
                    icons.forEachIndexed { idx, ic ->
                        val isSelected = selectedIconIndex == idx
                        Surface(
                            modifier = Modifier
                                .size(44.dp)
                                .clickable { selectedIconIndex = idx },
                            shape = CircleShape,
                            color = if (isSelected) ThemeColors.Primary else Color.Transparent,
                            tonalElevation = if (isSelected) AppDimension.Elevation.CARD else 0.dp
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = ic,
                                    contentDescription = null,
                                    tint = if (isSelected) Color.White else ColorProvider.textPrimary()
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(AppDimension.Padding.SM))
                Text(text = "Choose color")
                Row(horizontalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)) {
                    colors.forEach { c ->
                        val isCSelected = selectedColor == c
                        Surface(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { selectedColor = c },
                            color = c,
                            shape = CircleShape,
                            tonalElevation = if (isCSelected) AppDimension.Elevation.CARD else 0.dp
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                if (isCSelected) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(AppDimension.Padding.SM))
            }
        },
        confirmButton = {
            TextButton(onClick = {
                // Use selected values when saving
                onSave(name.ifBlank { "New Project" }, icons.getOrElse(selectedIconIndex) { Icons.Default.Star }, selectedColor)
            }) { Text(text = "Add") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(text = "Cancel") }
        }
    )
}

fun getDefaultProjects() = listOf(
    Project("web", "Website Redesign", Icons.Default.Star),
    Project("marketing", "Q4 Marketing", Icons.Default.Build),
    Project("mobile", "Mobile App Dev", Icons.Default.Phone),
    Project("feature", "New Feature", Icons.Default.Add)
)

fun getDefaultTodayTasks() = listOf(
    Task("1", "Finalize new logo concepts", TaskPriority.HIGH, "Oct 26"),
    Task("2", "Draft Q4 budget proposal", TaskPriority.MEDIUM, "Oct 26"),
    Task("3", "Review user feedback", TaskPriority.HIGH, "Overdue", isOverdue = true)
)

fun getDefaultUpcomingTasks() = listOf(
    Task("4", "Plan team offsite event", TaskPriority.LOW, "Nov 03"),
    Task("5", "Update project roadmap", TaskPriority.MEDIUM, "Nov 15")
)

// Preview
@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        navController = rememberNavController()
    )
}