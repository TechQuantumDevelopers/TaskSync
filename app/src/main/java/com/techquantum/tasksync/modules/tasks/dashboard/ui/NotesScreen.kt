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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techquantum.tasksync.data.enums.TaskPriority
import com.techquantum.tasksync.data.model.Project
import com.techquantum.tasksync.data.model.Task
import com.techquantum.tasksync.modules.tasks.addUpdate.navigation.TasksGraphs
import com.techquantum.tasksync.modules.tasks.dashboard.components.DashboardHeader
import com.techquantum.tasksync.modules.tasks.dashboard.components.ProjectsSection
import com.techquantum.tasksync.modules.tasks.dashboard.components.TasksGridList
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun DashboardScreen(
    onTaskMenuClick: (Task) -> Unit = {},
    navController: NavHostController
) {
    val activeTasksCount = 0
    val projects = getDefaultProjects()
    val todayTasks = getDefaultTodayTasks()
    val upcomingTasks = getDefaultUpcomingTasks()

    var selectedProject by remember { mutableStateOf(projects.firstOrNull()) }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(ThemeColors.BackgroundLight)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Section
                DashboardHeader(
                    activeTasksCount = activeTasksCount,
                    onNotificationClick = {
                        // open notifications screen
                    }
                )


                // Projects Section
                ProjectsSection(
                    projects = projects,
                    selectedProject = selectedProject,
                    onProjectClick = { project ->
                        selectedProject = project
                        navController.navigate(TasksGraphs.Tasks.withId(project.id))
                    }
                )

                // Today Section
                Text(
                    text = "Today",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ThemeColors.TextLight,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )

                TasksGridList(
                    tasks = todayTasks,
                    onTaskMenuClick = onTaskMenuClick
                )

                // Upcoming Section
                Text(
                    text = "Upcoming",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ThemeColors.TextLight,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 24.dp,
                        bottom = 8.dp
                    )
                )

                TasksGridList(
                    tasks = upcomingTasks,
                    onTaskMenuClick = onTaskMenuClick
                )

                Spacer(modifier = Modifier.height(96.dp))
            }

            // Floating Action Button
            FloatingActionButton(
                onClick = { /* Handle add */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
                    .size(56.dp)
                    .shadow(8.dp, CircleShape),
                containerColor = ThemeColors.Primary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

fun getDefaultProjects() = listOf(
    Project("all", "All Projects", Icons.Default.Menu),
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