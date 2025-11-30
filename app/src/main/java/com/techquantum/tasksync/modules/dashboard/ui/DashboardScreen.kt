package com.techquantum.tasksync.modules.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.techquantum.tasksync.R
import com.techquantum.tasksync.data.enums.NoteColor
import com.techquantum.tasksync.data.enums.Tab
import com.techquantum.tasksync.data.enums.TaskPriority
import com.techquantum.tasksync.data.model.Note
import com.techquantum.tasksync.data.model.Subtask
import com.techquantum.tasksync.data.model.Task
import com.techquantum.tasksync.modules.dashboard.components.*
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    var selectedTab by remember { mutableStateOf(Tab.TASKS) }
    var selectedFilter by remember { mutableStateOf("All") }

    // Sample data with mutable state
    val tasks = remember {
        mutableStateListOf(
            Task(
                id = "1",
                title = "Finalize project proposal",
                isCompleted = false,
                priority = TaskPriority.HIGH,
                subtasks = listOf(
                    Subtask("1", "Review project requirements", true),
                    Subtask("2", "Create content outline", true),
                    Subtask("3", "Write first draft", false),
                    Subtask("4", "Incorporate feedback", false),
                    Subtask("5", "Submit for final review", false)
                )
            ),
            Task(
                id = "2",
                title = "Call the design team for a sync-up",
                isCompleted = true,
                subtasks = listOf(
                    Subtask("1", "Prepare agenda", true),
                    Subtask("2", "Send calendar invite", true),
                    Subtask("3", "Follow up on action items", true)
                )
            ),
            Task(
                id = "3",
                title = "Draft weekly report",
                isCompleted = false,
                subtasks = listOf(
                    Subtask("1", "Collect data", false),
                    Subtask("2", "Analyze results", false),
                    Subtask("3", "Write summary", false),
                    Subtask("4", "Create charts", false),
                    Subtask("5", "Review with team", false),
                    Subtask("6", "Make revisions", false),
                    Subtask("7", "Final proofread", false),
                    Subtask("8", "Submit to manager", false)
                )
            )
        )
    }

    val notes = remember {
        listOf(
            Note(
                id = "1",
                title = "Project Phoenix Ideas",
                content = "Initial thoughts on rebranding, focusing on a cleaner aesthetic. We need to explore new color...",
                category = "MARKETING",
                color = NoteColor.ORANGE,
                isPinned = true
            ),
            Note(
                id = "2",
                title = "Meeting Notes",
                content = "Q4 roadmap discussion, key takeaways: prioritize customer feedback fea...",
                category = "PRODUCT",
                color = NoteColor.PINK,
                isPinned = true
            ),
             Note(
                id = "3",
                title = "Design System Updates",
                content = "Review the new typography scale and color palette. Ensure accessibility compliance...",
                category = "DESIGN",
                color = NoteColor.BLUE,
                isPinned = false
            ),
            Note(
                id = "4",
                title = "Team Building Event",
                content = "Ideas for the next team outing: bowling, escape room, or a park picnic...",
                category = "HR",
                color = NoteColor.GREEN,
                isPinned = false
            ),
            Note(
                id = "5",
                title = "Q1 Marketing Plan",
                content = "Focus on social media campaigns and content marketing. Explore influencer collaborations...",
                category = "MARKETING",
                color = NoteColor.ORANGE,
                isPinned = false
            ),
            Note(
                id = "6",
                title = "App Feature Ideas",
                content = "Gamification elements, AI-powered suggestions, Dark mode enhancements...",
                category = "IDEAS",
                color = NoteColor.PURPLE,
                isPinned = false
            )
        )
    }

    // Task Logic
    fun toggleTaskCompletion(task: Task, isChecked: Boolean) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            val updatedSubtasks = task.subtasks.map { it.copy(isCompleted = isChecked) }
            tasks[index] = task.copy(isCompleted = isChecked, subtasks = updatedSubtasks)
        }
    }

    fun toggleSubtaskCompletion(task: Task, subtask: Subtask, isChecked: Boolean) {
        val taskIndex = tasks.indexOfFirst { it.id == task.id }
        if (taskIndex != -1) {
            val currentTask = tasks[taskIndex]
            val updatedSubtasks = currentTask.subtasks.map {
                if (it.id == subtask.id) it.copy(isCompleted = isChecked) else it
            }
            
            // Check if all subtasks are completed to update parent task
            val allSubtasksCompleted = updatedSubtasks.all { it.isCompleted }
            // If parent was completed and we uncheck a subtask, parent becomes incomplete
            // If parent was incomplete and we check the last subtask, parent becomes complete
            val parentCompleted = if (isChecked) allSubtasksCompleted else false
            
            tasks[taskIndex] = currentTask.copy(isCompleted = parentCompleted, subtasks = updatedSubtasks)
        }
    }

    Scaffold(
        containerColor = ThemeColors.BackgroundDark,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add task/note */ },
                modifier = Modifier.size(AppDimension.IconSize.FAB),
                shape = CircleShape,
                containerColor = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    ThemeColors.FabGradientStart,
                                    ThemeColors.FabGradientEnd
                                )
                            ),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.dashboard_add_task_cd),
                        tint = Color.White,
                        modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = AppDimension.Spacing.SECTION_BOTTOM)
        ) {
            // Header
            item {
                Header()
            }

            // Search Bar
            item {
                SearchBar(modifier = Modifier.padding(vertical = AppDimension.Padding.MD))
            }

            // Tab Selector
            item {
                TabSelector(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            }

            if (selectedTab == Tab.TASKS) {
                // Today's Tasks Section
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = AppDimension.Padding.LG,
                                vertical = AppDimension.Padding.MD
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.dashboard_today_tasks),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = ThemeColors.CardBackground
                            )
                            Spacer(modifier = Modifier.width(AppDimension.Padding.SM))
                            val totalCompleted = tasks.count { it.isCompleted }
                            Text(
                                text = stringResource(
                                    R.string.dashboard_today_tasks_count,
                                    totalCompleted,
                                    tasks.size
                                ),
                                style = MaterialTheme.typography.titleMedium,
                                color = ThemeColors.TextSecondary
                            )
                        }

                        TextButton(onClick = { /* See all */ }) {
                            Text(
                                text = stringResource(R.string.dashboard_see_all),
                                color = ThemeColors.Primary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                // Tasks List
                items(tasks) { task ->
                    ExpandableTaskItem(
                        task = task,
                        onTaskCheckedChange = { isChecked -> toggleTaskCompletion(task, isChecked) },
                        onSubtaskCheckedChange = { subtask, isChecked -> toggleSubtaskCompletion(task, subtask, isChecked) }
                    )
                }

                // Spacing before Pinned Notes (only show if we want pinned notes in Tasks view, 
                // but usually "Tasks" tab focuses on tasks. 
                // However, the design might have them. Let's keep Pinned Notes at bottom of Tasks view for now as per original design)
                item {
                    Spacer(modifier = Modifier.height(AppDimension.Settings.SECTION_SPACING))
                }

                // Pinned Notes Section
                item {
                    Text(
                        text = stringResource(R.string.dashboard_pinned_notes),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = ThemeColors.CardBackground,
                        modifier = Modifier.padding(horizontal = AppDimension.Padding.LG)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(AppDimension.Padding.LG))
                }

                // Notes Row
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = AppDimension.Padding.LG),
                        horizontalArrangement = Arrangement.spacedBy(AppDimension.Padding.MD)
                    ) {
                        items(notes.take(2)) { note -> // Show only a few pinned notes
                            PinnedNoteCard(note = note)
                        }
                    }
                }
            } else {
                // Notes Tab Content
                item {
                    Spacer(modifier = Modifier.height(AppDimension.Padding.MD))
                }

                // Pinned Notes Section
                item {
                    val pinnedNotes = notes.filter { it.isPinned }
                    if (pinnedNotes.isNotEmpty()) {
                        PinnedNotesSection(notes = pinnedNotes)
                        Spacer(modifier = Modifier.height(AppDimension.Padding.XL))
                    }
                }

                // All Notes Header
                item {
                    Text(
                        text = stringResource(R.string.dashboard_all_notes),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = ThemeColors.CardBackground,
                        modifier = Modifier.padding(horizontal = AppDimension.Padding.LG)
                    )
                    Spacer(modifier = Modifier.height(AppDimension.Padding.MD))
                }

                // Filter Chips
                item {
                    NoteFilterChips(
                        selectedFilter = selectedFilter,
                        onFilterSelected = { selectedFilter = it }
                    )
                    Spacer(modifier = Modifier.height(AppDimension.Padding.LG))
                }
                
                // Notes Grid
                item {
                    val filteredNotes = if (selectedFilter == "All") {
                        notes
                    } else {
                        notes.filter { it.category.equals(selectedFilter, ignoreCase = true) }
                    }
                    
                    NotesGrid(notes = filteredNotes)
                }
            }
        }
    }
}