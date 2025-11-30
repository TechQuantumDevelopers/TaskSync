package com.techquantum.tasksync.modules.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.techquantum.tasksync.R
import com.techquantum.tasksync.data.enums.NoteColor
import com.techquantum.tasksync.data.model.Note
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun PinnedNotesSection(
    notes: List<Note>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.dashboard_pinned_notes),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = ThemeColors.CardBackground,
            modifier = Modifier.padding(horizontal = AppDimension.Padding.LG)
        )
        
        Spacer(modifier = Modifier.height(AppDimension.Padding.MD))
        
        LazyRow(
            contentPadding = PaddingValues(horizontal = AppDimension.Padding.LG),
            horizontalArrangement = Arrangement.spacedBy(AppDimension.Padding.MD)
        ) {
            items(notes) { note ->
                PinnedNoteCard(note = note)
            }
        }
    }
}

@Composable
fun PinnedNoteCard(
    note: Note,
    modifier: Modifier = Modifier
) {
    val barColor = when (note.color) {
        NoteColor.ORANGE -> ThemeColors.NoteOrange
        NoteColor.PINK -> ThemeColors.NotePink
        NoteColor.BLUE -> ThemeColors.NoteBlue
        NoteColor.GREEN -> ThemeColors.NoteGreen
        else -> ThemeColors.NoteOrange
    }

    Box(
        modifier = modifier
            .width(AppDimension.Dashboard.PINNED_NOTE_WIDTH)
            .height(AppDimension.Dashboard.PINNED_NOTE_HEIGHT)
            .clip(RoundedCornerShape(AppDimension.Radius.LG))
            .background(ThemeColors.SurfaceDark)
    ) {
        // Colored Bar
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(barColor)
                .align(Alignment.CenterStart)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = AppDimension.Padding.LG, // Space for bar
                    top = AppDimension.Padding.MD,
                    end = AppDimension.Padding.MD,
                    bottom = AppDimension.Padding.MD
                )
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = ThemeColors.CardBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(AppDimension.Padding.XS))
            
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                color = ThemeColors.TextSecondary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            
            Spacer(modifier = Modifier.height(AppDimension.Padding.SM))
            
            Text(
                text = note.category.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = barColor
            )
        }
    }
}

@Composable
fun NoteFilterChips(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filters = listOf(
        stringResource(R.string.dashboard_filter_all),
        stringResource(R.string.dashboard_filter_work),
        stringResource(R.string.dashboard_filter_personal),
        stringResource(R.string.dashboard_filter_ideas),
        stringResource(R.string.dashboard_filter_marketing)
    )

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = AppDimension.Padding.LG),
        horizontalArrangement = Arrangement.spacedBy(AppDimension.Padding.SM)
    ) {
        items(filters) { filter ->
            val isSelected = filter == selectedFilter
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (isSelected) ThemeColors.ChipSelected else ThemeColors.ChipUnselected)
                    .clickable { onFilterSelected(filter) }
                    .padding(horizontal = AppDimension.Padding.LG, vertical = AppDimension.Padding.SM),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = filter,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium,
                    color = if (isSelected) ThemeColors.ChipTextSelected else ThemeColors.ChipTextUnselected
                )
            }
        }
    }
}

@Composable
fun NotesGrid(
    notes: List<Note>,
    modifier: Modifier = Modifier
) {
    // Since we are inside a LazyColumn in DashboardScreen, we cannot use LazyVerticalStaggeredGrid directly with infinite height.
    // We will use a FlowRow or just a Column with Rows for now as implemented in the previous DashboardScreen, 
    // OR we can use a fixed height grid if we want scrolling within the tab, but usually the whole screen scrolls.
    // Given the constraints and the previous implementation using chunked rows, let's stick to a custom staggered layout or just two columns.
    // For simplicity and robustness in a scrollable parent, we'll render items in a Column of Rows (2 items per row).
    
    Column(
        modifier = modifier.padding(horizontal = AppDimension.Padding.LG),
        verticalArrangement = Arrangement.spacedBy(AppDimension.Dashboard.NOTE_GRID_SPACING)
    ) {
        val chunkedNotes = notes.chunked(2)
        chunkedNotes.forEach { rowNotes ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppDimension.Dashboard.NOTE_GRID_SPACING)
            ) {
                rowNotes.forEach { note ->
                    NoteCard(
                        note = note,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowNotes.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    modifier: Modifier = Modifier
) {
    val barColor = when (note.color) {
        NoteColor.ORANGE -> ThemeColors.NoteOrange
        NoteColor.PINK -> ThemeColors.NotePink
        NoteColor.BLUE -> ThemeColors.NoteBlue
        NoteColor.GREEN -> ThemeColors.NoteGreen
        else -> ThemeColors.NoteOrange
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(AppDimension.Radius.LG))
            .background(ThemeColors.SurfaceDark)
            .border(1.dp, ThemeColors.CardDark, RoundedCornerShape(AppDimension.Radius.LG))
    ) {
        // Colored Bar
        Box(
            modifier = Modifier
                .matchParentSize() // Fill the card
                .width(4.dp) // But only 4dp wide? No, we want a strip on the left.
        ) {
             Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .background(barColor)
                    .align(Alignment.CenterStart)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = AppDimension.Padding.LG,
                    top = AppDimension.Padding.MD,
                    end = AppDimension.Padding.MD,
                    bottom = AppDimension.Padding.MD
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = ThemeColors.CardBackground,
                    modifier = Modifier.weight(1f)
                )
                
                if (note.isPinned) {
                    Icon(
                        imageVector = Icons.Default.PushPin,
                        contentDescription = "Pinned",
                        tint = ThemeColors.TextSecondary,
                        modifier = Modifier.size(AppDimension.IconSize.SMALL)
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppDimension.Padding.SM))

            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                color = ThemeColors.TextSecondary,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(AppDimension.Padding.MD))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = note.category,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = barColor
                )
                
                Text(
                    text = "Nov 28", // Placeholder date
                    style = MaterialTheme.typography.labelSmall,
                    color = ThemeColors.TextDisabled
                )
            }
        }
    }
}
