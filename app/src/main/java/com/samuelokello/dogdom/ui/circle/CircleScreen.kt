package com.samuelokello.dogdom.ui.circle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.ui.circle.components.CircleItemComponent
import com.samuelokello.dogdom.ui.circle.components.CircleToJoinComponent
import com.samuelokello.dogdom.ui.shared.components.DogDomTopAppBar
import com.samuelokello.dogdom.ui.shared.components.DogdomSearchBar
import com.samuelokello.dogdom.ui.shared.components.QuickActionCard

@Composable
fun CircleScreen(
    modifier: Modifier = Modifier,
    onCircleClick: (Int) -> Unit = {}
    ) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DogDomTopAppBar(
            title = "Circle",
            modifier = modifier,
            actions = {
                Row {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_scan),
                            contentDescription = stringResource(R.string.navigate_back)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = stringResource(R.string.navigate_back)
                        )
                    }
                }
            }
        )
        CircleContent(onCircleClick = onCircleClick)
    }

}

@Composable
fun CircleContent(modifier: Modifier = Modifier, onCircleClick: (Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            DogdomSearchBar(
                modifier = modifier,
                query = "",
                onQueryChange = {},
                onSearch = {},
                active = false,
                onActiveChange = {},
                enabled = true
            ) { }
            Spacer(modifier.height(16.dp))
            QuickActionCard(
                modifier = modifier,
                quickActionImages = DataSource.takeHomeImages,
                quickActionTitle = "How do you",
                quickActionDescription = "Create your circle?",
                buttonLabel = "Create",
                onButtonClick = {}
            )
        }
        Column {
            Row {
                Text(text = "Popular Circles", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "More", style = MaterialTheme.typography.bodyMedium.copy(Color.Gray))
            }
        }
        Spacer(modifier.height(8.dp))
        Column {
            LazyRow {
                items(DataSource.circleItems) {
                    CircleItemComponent(
                        item = it,
                        modifier = modifier,
                        onClick = onCircleClick
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }

        Spacer(modifier.height(8.dp))

        Column {
            Row {
                Text(text = "The Circle to Join", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "More", style = MaterialTheme.typography.bodyMedium.copy(Color.Gray))
            }
            Spacer(modifier.height(16.dp))
            LazyColumn {
                items(
                    DataSource.circleItems
                ) {
                    CircleToJoinComponent(
                        item = it,
                        modifier = modifier,
                        onClick = onCircleClick
                    )
                    Spacer(modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CirclePrev() {
    CircleScreen()
}