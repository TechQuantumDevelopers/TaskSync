package com.techquantum.tasksync.modules.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.techquantum.tasksync.R
import com.techquantum.tasksync.ui.theme.AppDimension
import com.techquantum.tasksync.ui.theme.ThemeColors

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }
    
    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppDimension.Padding.LG),
        placeholder = {
            Text(
                text = stringResource(R.string.dashboard_search_hint),
                color = ThemeColors.TextDisabled
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = ThemeColors.TextDisabled
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = ThemeColors.SurfaceDark,
            unfocusedContainerColor = ThemeColors.SurfaceDark,
            focusedTextColor = ThemeColors.CardBackground,
            unfocusedTextColor = ThemeColors.CardBackground,
            cursorColor = ThemeColors.Primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(AppDimension.Radius.LG),
        singleLine = true
    )
}
