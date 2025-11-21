package com.techquantum.tasksync.modules.tasks.dashboard.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.data.model.Project

@Composable
fun ProjectsSection(
    projects: List<Project>,
    selectedProject: Project?,
    onProjectClick: (Project) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(projects) { project ->
            ProjectItem(
                project = project,
                isSelected = project == selectedProject,
                onClick = { onProjectClick(project) }
            )
        }
    }
}