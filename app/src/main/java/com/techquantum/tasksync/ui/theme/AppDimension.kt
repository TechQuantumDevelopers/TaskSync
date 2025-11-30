package com.techquantum.tasksync.ui.theme

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppDimension {
    object FontSize {
        val XS = 11.sp
        val SM = 12.sp
        val MD = 14.sp
        val LG = 16.sp
        val XL = 18.sp
        val XXL = 22.sp
        val LOGO = 36.sp
    }

    object Padding {
        val XS = 4.dp
        val SM = 8.dp
        val MD = 12.dp
        val LG = 16.dp
        val XL = 24.dp
        val XXL = 32.dp
    }

    object Radius {
        val SM = 8.dp
        val MD = 12.dp
        val LG = 20.dp
    }

    object IconSize {
        val SMALL = 20.dp
        val MEDIUM = 24.dp
        val LARGE = 28.dp
        val XLARGE = 30.dp
        val FAB = 56.dp
        val BUTTON_HEIGHT = 48.dp
    }

    object Elevation {
        val CARD = 8.dp
    }

    object Spacing {
        val GRID_GAP = 8.dp
        val SECTION_BOTTOM = 96.dp
    }

    // Heights / specific dimension constants
    object Heights {
        val TEXTAREA_MIN = 120.dp
        val SETTINGS_OPTION_ITEM = 72.dp
    }
    
    // Settings specific dimensions
    object Settings {
        val ICON_BACKGROUND_SIZE = 48.dp
        val BOTTOM_SHEET_CORNER_RADIUS = 24.dp
        val SECTION_SPACING = 24.dp
    }
    
    // Dashboard specific dimensions
    object Dashboard {
        val SUBTASK_INDENT = 48.dp
        val SUBTASK_LINE_WIDTH = 2.dp
        val SUBTASK_LINE_START_OFFSET = 11.dp
        val NOTE_CARD_HEIGHT = 160.dp
        val NOTE_CARD_WIDTH = 220.dp
        val TASK_ITEM_MIN_HEIGHT = 56.dp
        val NOTE_GRID_SPACING = 12.dp
        val PINNED_NOTE_WIDTH = 200.dp
        val PINNED_NOTE_HEIGHT = 140.dp
    }
}
