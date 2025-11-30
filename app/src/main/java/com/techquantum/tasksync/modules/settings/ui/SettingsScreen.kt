package com.techquantum.tasksync.modules.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.techquantum.tasksync.R
import com.techquantum.tasksync.modules.settings.components.BackupRestoreBottomSheet
import com.techquantum.tasksync.modules.settings.components.BottomSheetMode
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

enum class ThemeMode {
    LIGHT, DARK, SYSTEM
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController? = null) {
    var selectedTheme by remember { mutableStateOf(ThemeMode.SYSTEM) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetMode by remember { mutableStateOf(BottomSheetMode.BACKUP) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings_title),
                        fontWeight = FontWeight.Bold,
                        color = ThemeColors.CardBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.settings_back_cd),
                            tint = ThemeColors.CardBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = ThemeColors.BackgroundDark)
            )
        },
        containerColor = ThemeColors.BackgroundDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = AppDimension.Padding.LG)
        ) {
            Spacer(modifier = Modifier.height(AppDimension.Padding.LG))
            Text(
                text = stringResource(R.string.settings_section_appearance),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = ThemeColors.TextSecondary,
                modifier = Modifier.padding(
                    start = AppDimension.Padding.XS,
                    bottom = AppDimension.Padding.MD
                )
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(AppDimension.Radius.LG),
                colors = CardDefaults.cardColors(containerColor = ThemeColors.SurfaceDark)
            ) {
                Column(modifier = Modifier.padding(AppDimension.Padding.LG)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(AppDimension.Settings.ICON_BACKGROUND_SIZE)
                                .clip(CircleShape)
                                .background(ThemeColors.IconBackgroundBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Brightness6,
                                null,
                                tint = ThemeColors.Primary,
                                modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
                            )
                        }
                        Spacer(modifier = Modifier.width(AppDimension.Padding.MD))
                        Text(
                            stringResource(R.string.settings_theme_label),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            color = ThemeColors.CardBackground
                        )
                    }
                    Spacer(modifier = Modifier.height(AppDimension.Padding.LG))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppDimension.Padding.MD)
                    ) {
                        ThemeCard(
                            Icons.Default.LightMode,
                            stringResource(R.string.settings_theme_light),
                            selectedTheme == ThemeMode.LIGHT,
                            { selectedTheme = ThemeMode.LIGHT },
                            Modifier.weight(1f)
                        )
                        ThemeCard(
                            Icons.Default.DarkMode,
                            stringResource(R.string.settings_theme_dark),
                            selectedTheme == ThemeMode.DARK,
                            { selectedTheme = ThemeMode.DARK },
                            Modifier.weight(1f)
                        )
                        ThemeCard(
                            Icons.Default.PhoneAndroid,
                            stringResource(R.string.settings_theme_system),
                            selectedTheme == ThemeMode.SYSTEM,
                            { selectedTheme = ThemeMode.SYSTEM },
                            Modifier.weight(1f)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(AppDimension.Settings.SECTION_SPACING))
            Text(
                stringResource(R.string.settings_section_data_management),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = ThemeColors.TextSecondary,
                modifier = Modifier.padding(
                    start = AppDimension.Padding.XS,
                    bottom = AppDimension.Padding.MD
                )
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(AppDimension.Radius.LG),
                colors = CardDefaults.cardColors(containerColor = ThemeColors.SurfaceDark)
            ) {
                Column {
                    OptionRow(
                        Icons.Default.ArrowUpward,
                        ThemeColors.IconBackgroundGreen,
                        ThemeColors.Success,
                        stringResource(R.string.settings_backup_title),
                        stringResource(R.string.settings_backup_description)
                    ) { bottomSheetMode = BottomSheetMode.BACKUP; showBottomSheet = true }
                    HorizontalDivider(color = ThemeColors.TextDisabled.copy(alpha = 0.1f))
                    OptionRow(
                        Icons.Default.ArrowDownward,
                        ThemeColors.IconBackgroundOrange,
                        Color(0xFFFF8C42),
                        stringResource(R.string.settings_restore_title),
                        stringResource(R.string.settings_restore_description)
                    ) { bottomSheetMode = BottomSheetMode.RESTORE; showBottomSheet = true }
                }
            }
            Spacer(modifier = Modifier.height(AppDimension.Padding.XXL))
        }
    }
    if (showBottomSheet) BackupRestoreBottomSheet(
        bottomSheetMode,
        { showBottomSheet = false },
        { showBottomSheet = false },
        { showBottomSheet = false })
}

@Composable
private fun ThemeCard(
    icon: ImageVector,
    label: String,
    sel: Boolean,
    onClick: () -> Unit,
    mod: Modifier
) {
    Card(
        onClick = onClick,
        modifier = mod,
        shape = RoundedCornerShape(AppDimension.Radius.MD),
        colors = CardDefaults.cardColors(containerColor = if (sel) ThemeColors.Primary else ThemeColors.CardDark)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(AppDimension.Padding.LG),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                label,
                tint = if (sel) Color.White else ThemeColors.TextSecondary,
                modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
            )
            Spacer(Modifier.height(AppDimension.Padding.SM))
            Text(
                label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (sel) FontWeight.SemiBold else FontWeight.Normal,
                color = if (sel) Color.White else ThemeColors.TextSecondary
            )
        }
    }
}

@Composable
private fun OptionRow(
    icon: ImageVector,
    bg: Color,
    tint: Color,
    title: String,
    desc: String,
    click: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = click)
            .padding(AppDimension.Padding.LG),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(AppDimension.Settings.ICON_BACKGROUND_SIZE)
                .clip(CircleShape)
                .background(bg), Alignment.Center
        ) {
            Icon(icon, null, tint = tint, modifier = Modifier.size(AppDimension.IconSize.MEDIUM))
        }
        Spacer(Modifier.width(AppDimension.Padding.MD))
        Column(Modifier.weight(1f)) {
            Text(
                title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = ThemeColors.CardBackground
            )
            Spacer(Modifier.height(AppDimension.Padding.XS))
            Text(
                desc,
                style = MaterialTheme.typography.bodySmall,
                color = ThemeColors.TextSecondary
            )
        }
        Icon(
            Icons.Default.ChevronRight,
            null,
            tint = ThemeColors.TextDisabled,
            modifier = Modifier.size(AppDimension.IconSize.MEDIUM)
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() = SettingsScreen()