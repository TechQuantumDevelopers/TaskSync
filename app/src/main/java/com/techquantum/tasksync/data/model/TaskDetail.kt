package com.techquantum.tasksync.data.model

import com.techquantum.tasksync.data.enums.TaskPriority

data class TaskDetail(
    val id: String,
    val title: String,
    val projectName: String,
    val priority: TaskPriority,
    val dueDate: String,
    val category: String,
    val quarter: String,
    val description: String,
    val subtasks: List<Subtask>,
    val reminder: String
)
