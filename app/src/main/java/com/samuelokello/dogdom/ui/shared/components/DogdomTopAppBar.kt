package com.samuelokello.dogdom.ui.shared.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.samuelokello.dogdom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDomTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
    navigationIcon: @Composable (() -> Unit)? = null,
    canNavigateBack: Boolean = false,
    isTitleCentered: Boolean = false
) {
    TopAppBar(
        modifier = modifier,
        title = {
            if (isTitleCentered) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    title?.let { Text(text = it) }
                }
            } else {
                title?.let { Text(text = it) }
            }
        },
        actions = { actions() },
        navigationIcon = {
            if (canNavigateBack) {
                navigationIcon?.invoke() ?: IconButton(onClick = onNavigationIconClick ?: {}) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(
                            R.string.navigate_back
                        )
                    )
                }
            }
        }
    )
}
