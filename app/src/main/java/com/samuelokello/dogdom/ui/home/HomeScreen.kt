package com.samuelokello.dogdom.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.model.Feature
import com.samuelokello.dogdom.model.Post
import com.samuelokello.dogdom.ui.home.components.FeatureSection
import com.samuelokello.dogdom.ui.home.components.PostCard
import com.samuelokello.dogdom.ui.home.components.QuickActions
import com.samuelokello.dogdom.ui.home.model.HomeIcon
import com.samuelokello.dogdom.ui.theme.CustomOrange

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        HomeScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(viewModel: HomeViewModel = HomeViewModel(DataSource)) {
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Select", "Discover")

    val state by viewModel.state.collectAsState()


    CenterAlignedTopAppBar(
        title = {
            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier
                    .width(200.dp),
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[tabIndex])
                            .height(2.dp)
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        Canvas(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            drawLine(
                                color = CustomOrange,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                strokeWidth = 2f,
                            )
                        }
                    }
                },
                divider = {},
                containerColor = Color.Transparent,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = {
                            Text(
                                text = title,
                                color = if (tabIndex == index) Color.DarkGray else Color.LightGray.copy(
                                    alpha = 0.6f
                                ),
                                fontWeight = if (tabIndex == index) FontWeight.Bold else FontWeight.Medium,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 18.sp
                            )
                        }
                    )
                }

            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = {
            IconButton(
                onClick = {},
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = "Notifications"
                )
            }
        },
        modifier = Modifier
    )

    when (tabIndex) {
        0 -> SelectScreen(
            posts = state.selectTabPosts,
            feature = state.feature
            )
        1 -> DiscoverScreen(posts = state.discoverTabPosts)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun SelectScreen(
    posts: List<Post>,
    feature: List<Feature>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = rememberLazyListState(),
        ) {
            item {
                Column {
                    QuickActions(
                        homeIcons = listOf(
                            HomeIcon(
                                icon = R.drawable.shoping_cart,
                                title = "Ranking"
                            ),
                            HomeIcon(
                                icon = R.drawable.revelation,
                                title = "Discuss"
                            ),
                            HomeIcon(
                                icon = R.drawable.foster_care,
                                title = "Surrounding"
                            )
                        )
                    )
                }
            }

            itemsIndexed(posts) { index,post ->
                Column {
                    PostCard(post = post)
                    if (index == 2) {
                        FeatureSection(features = feature)
                    }
                }
            }

        }
    }
}

@Composable
fun DiscoverScreen(posts: List<Post>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = rememberLazyListState(),
        ) {
            item {
                Column {
                    QuickActions(
                        homeIcons = listOf(
                            HomeIcon(
                                icon = R.drawable.shoping_cart,
                                title = "Ranking"
                            ),
                            HomeIcon(
                                icon = R.drawable.revelation,
                                title = "Discuss"
                            ),
                            HomeIcon(
                                icon = R.drawable.foster_care,
                                title = "Surrounding"
                            ),
                        ),
                        discoverTab = true
                    )
                }
            }

            items(posts) { post ->
                Column {
                    PostCard(post = post)
                }
            }
        }
    }

}

