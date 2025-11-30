package com.techquantum.tasksync.data.model

import com.techquantum.tasksync.data.enums.TaskPriority

data class Task(
    val id: String,
    val title: String,
    val isCompleted: Boolean = false,
    val dueInHours: Int? = null,
    val priority: TaskPriority = TaskPriority.NONE,
    val hasCalendar: Boolean = false,
    val subtasks: List<Subtask> = emptyList()
)

data class Subtask(
    val id: String,
    val title: String,
    val isCompleted: Boolean = false
)