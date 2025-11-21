package com.techquantum.tasksync.data.model

import com.techquantum.tasksync.data.enums.TaskPriority

data class Task(
    val id: String? = "",
    val title: String? = "",
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val dueDate: String? = "",
    val isOverdue: Boolean = false
)