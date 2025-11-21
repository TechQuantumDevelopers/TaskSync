package com.techquantum.tasksync.modules.tasks.addUpdate.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class TasksGraphs(val route: String) {
    data class Tasks(val taskId: String? = null) : TasksGraphs("notes") {
        companion object {
            const val ARG_TASK_ID = "taskId"
            fun withId(id: String?) = if (id.isNullOrEmpty()) "notes" else "notes/$id"
            const val ROUTE_WITH_ARG = "notes/{${ARG_TASK_ID}}"
        }
    }
}