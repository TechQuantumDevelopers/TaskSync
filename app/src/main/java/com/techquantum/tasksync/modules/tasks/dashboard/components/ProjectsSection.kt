package com.techquantum.tasksync.modules.tasks.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.data.model.Project
import com.techquantum.tasksync.ui.theme.AppDimension
import androidx.compose.ui.res.stringResource
import com.techquantum.tasksync.R
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun ProjectsSection(
    projects: List<Project>,
    selectedProject: Project?,
    onProjectClick: (Project) -> Unit,
    onAddProject: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = AppDimension.Padding.MD)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppDimension.Padding.LG),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.dashboard_projects),
                fontSize = AppDimension.FontSize.LG
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onAddProject) {
                    Text(
                        text = stringResource(R.string.dashboard_view_all),
                        fontSize = AppDimension.FontSize.MD
                    )
                }
                IconButton(onClick = onAddProject) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.common_add_task_cd)
                    )
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = AppDimension.Padding.LG),
            horizontalArrangement = Arrangement.spacedBy(AppDimension.Spacing.GRID_GAP)
        ) {
            items(projects) { project ->
                ProjectItem(
                    project = project,
                    isSelected = project == selectedProject,
                    onClick = { onProjectClick(project) })
            }
        }
    }
}