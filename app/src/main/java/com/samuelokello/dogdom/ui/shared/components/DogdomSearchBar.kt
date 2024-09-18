package com.samuelokello.dogdom.ui.shared.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.samuelokello.dogdom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogdomSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    active: Boolean,
    enabled: Boolean,
    content: @Composable (ColumnScope) -> Unit
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        placeholder = { Text(text = "Send the sample") },
        active = active,
        onActiveChange = onActiveChange,
        onSearch = onSearch,
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = null,
                tint = Color.LightGray
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_mic),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp),
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = SearchBarDefaults.colors(
            containerColor = Color.LightGray.copy(alpha = 0.1f),
        ),
        content = content
    )
}