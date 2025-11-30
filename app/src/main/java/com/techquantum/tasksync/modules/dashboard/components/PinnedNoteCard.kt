package com.techquantum.tasksync.modules.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.data.enums.NoteColor
import com.techquantum.tasksync.data.model.Note
import com.techquantum.tasksync.ui.theme.AppDimension

@Composable
fun PinnedNoteCard(
    note: Note,
    modifier: Modifier = Modifier
) {
    // Title
    Text(
        text = note.title,
        style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )

    Spacer(modifier = Modifier.height(AppDimension.Padding.SM))

    // Content
    Text(
        text = note.content,
        style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
        color = Color.White.copy(alpha = 0.9f),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
//        modifier = Modifier.weight(1f)
    )

    Spacer(modifier = Modifier.height(AppDimension.Padding.SM))

    // Category Badge
    Box(
        modifier = Modifier
            .background(
                color = Color.White.copy(alpha = 0.25f),
                shape = RoundedCornerShape(AppDimension.Radius.SM)
            )
            .padding(
                horizontal = AppDimension.Padding.MD,
                vertical = AppDimension.Padding.XS
            ),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            text = note.category.uppercase(),
            style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun PinnedNoteCardPreview() {
    PinnedNoteCard(
        note = Note(
            id = "1",
            title = "Meeting Notes",
            content = "Discuss project milestones and deliverables for the upcoming quarter.",
            category = "Work",
            color = NoteColor.ORANGE
        ),
        modifier = Modifier
            .width(200.dp)
            .background(
                color = Color(0xFF6200EE),
                shape = RoundedCornerShape(AppDimension.Radius.MD)
            )
            .padding(AppDimension.Padding.LG)
    )
}