package com.techquantum.tasksync.modules.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

enum class BottomSheetMode {
    BACKUP, RESTORE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackupRestoreBottomSheet(
    mode: BottomSheetMode,
    onDismiss: () -> Unit,
    onExportClick: () -> Unit = {},
    onImportClick: () -> Unit = {}
) {
    var showImportDialog by remember { mutableStateOf(false) }
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = ThemeColors.SurfaceDark,
        shape = RoundedCornerShape(
            topStart = AppDimension.Settings.BOTTOM_SHEET_CORNER_RADIUS,
            topEnd = AppDimension.Settings.BOTTOM_SHEET_CORNER_RADIUS
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimension.Padding.XL)
        ) {
            // Title
            Text(
                text = stringResource(R.string.backup_sheet_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = ThemeColors.CardBackground,
                modifier = Modifier.padding(bottom = AppDimension.Padding.XL)
            )
            
            when (mode) {
                BottomSheetMode.BACKUP -> {
                    BackupContent(onExportClick = onExportClick)
                }
                BottomSheetMode.RESTORE -> {
                    showImportDialog = true
                }
            }
            
            Spacer(modifier = Modifier.height(AppDimension.Padding.LG))
        }
    }
    
    // Import Confirmation Dialog
    if (showImportDialog) {
        AlertDialog(
            onDismissRequest = { 
                showImportDialog = false
                onDismiss()
            },
            containerColor = ThemeColors.CardDark,
            title = {
                Text(
                    text = stringResource(R.string.restore_dialog_title),
                    color = ThemeColors.CardBackground,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.restore_dialog_message),
                    color = ThemeColors.TextSecondary
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onImportClick()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ThemeColors.Danger
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(AppDimension.Radius.MD)
                ) {
                    Text(
                        text = stringResource(R.string.restore_action_import),
                        modifier = Modifier.padding(vertical = AppDimension.Padding.SM)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showImportDialog = false
                        onDismiss()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.restore_action_cancel),
                        color = ThemeColors.Primary
                    )
                }
            }
        )
    }
}

@Composable
private fun BackupContent(onExportClick: () -> Unit) {
    Column {
        // Export Data Section
        Text(
            text = stringResource(R.string.backup_export_section_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = ThemeColors.TextSecondary,
            modifier = Modifier.padding(bottom = AppDimension.Padding.MD)
        )
        
        Text(
            text = stringResource(R.string.backup_export_description),
            style = MaterialTheme.typography.bodyMedium,
            color = ThemeColors.TextSecondary,
            modifier = Modifier.padding(bottom = AppDimension.Padding.LG)
        )
        
        // Export Button
        Button(
            onClick = onExportClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = ThemeColors.Primary
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(AppDimension.Radius.MD)
        ) {
            Text(
                text = stringResource(R.string.backup_action_export),
                modifier = Modifier.padding(vertical = AppDimension.Padding.SM)
            )
        }
        
        Spacer(modifier = Modifier.height(AppDimension.Padding.LG))
        
        // Last Backup Info
        Text(
            text = stringResource(R.string.backup_last_backup_label, "Today, 10:45 AM"),
            style = MaterialTheme.typography.bodySmall,
            color = ThemeColors.TextDisabled,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun BackupRestoreBottomSheetPreview() {
    BackupRestoreBottomSheet(
        mode = BottomSheetMode.BACKUP,
        onDismiss = {}
    )
}