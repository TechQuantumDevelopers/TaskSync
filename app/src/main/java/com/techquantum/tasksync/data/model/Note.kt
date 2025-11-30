package com.techquantum.tasksync.data.model

import com.techquantum.tasksync.data.enums.NoteColor

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val category: String,
    val color: NoteColor
)