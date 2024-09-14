package com.samuelokello.dogdom.ui.home

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.model.Feature
import com.samuelokello.dogdom.model.Post
import com.samuelokello.dogdom.navigation.DogdomScreen
import com.samuelokello.dogdom.navigation.bottom_navigation.BottomNavigationBar
import com.samuelokello.dogdom.ui.home.components.FeatureSection
import com.samuelokello.dogdom.ui.home.components.PostCard
import com.samuelokello.dogdom.ui.home.components.QuickActions
import com.samuelokello.dogdom.ui.home.model.HomeIcon
import com.samuelokello.dogdom.ui.theme.CustomOrange

enum class HomeTab {
    Select, Discover
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = HomeViewModel(DataSource),
    onPostClick: (postId: Int) -> Unit,
    onBottomNavItemClick:(DogdomScreen) -> Unit,
    showBottomNavigation: Boolean,
    onTabChanged: (HomeTab) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BottomNavigationBar(
        showBottomBar = showBottomNavigation,
        onItemClick = onBottomNavItemClick
    ){
        HomeScreenContent(
            state = state,
            onTabChanged = onTabChanged,
            onPostClick = onPostClick
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    state: HomeUiState,
    onTabChanged: (HomeTab) -> Unit,
    onPostClick: (postId: Int) -> Unit
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                TabRow(
                    selectedTabIndex = state.currentTab.ordinal,
                    modifier = Modifier.width(200.dp),
                    indicator = { tabPositions ->
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[state.currentTab.ordinal])
                                .height(2.dp)
                                .fillMaxSize()
                                .padding(horizontal = 16.dp)
                        ) {
                            Canvas(modifier = Modifier.fillMaxSize()) {
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
                    HomeTab.entries.forEach { tab ->
                        Tab(
                            selected = state.currentTab == tab,
                            onClick = {
                                Log.d("Home Screen", "Tab selected: ${tab.name}")
                                onTabChanged(tab)
                            },
                            text = {
                                Text(
                                    text = tab.name,
                                    color = if (state.currentTab == tab) Color.DarkGray else Color.LightGray.copy(
                                        alpha = 0.6f
                                    ),
                                    fontWeight = if (state.currentTab == tab) FontWeight.Bold else FontWeight.Medium,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 18.sp
                                )
                            }
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
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

        when (state.currentTab) {
            HomeTab.Select -> SelectScreen(
                posts = state.selectTabPosts,
                feature = state.feature,
                onPostClick = {
                    onPostClick(it)
                }
            )

            HomeTab.Discover -> DiscoverScreen(
                posts = state.discoverTabPosts,
                onPostClick = { onPostClick(it) }
            )
        }
    }
}

@Composable
fun SelectScreen(
    posts: List<Post>,
    feature: List<Feature>,
    onPostClick: (postId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
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

            itemsIndexed(posts) { index, post ->
                Column {
                    PostCard(
                        post = post,
                        event = {},
                        onPostClick = { onPostClick(post.id) },
                        selectTab = true
                    )
                    if (index == 2) {
                        FeatureSection(features = feature)
                    }
                }
            }

        }
    }
}

@Composable
fun DiscoverScreen(
    posts: List<Post>,
    onPostClick: (postId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
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
                    PostCard(
                        post = post,
                        event = {},
                        selectTab = false,
                        onPostClick = { onPostClick(post.id) }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DiscoverScreenPrev() {
    DiscoverScreen(
        posts = DataSource.discoverPosts,
        onPostClick = {}
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun HomePrev() {
//    HomeScreen(
//        onPostClick = {},
//        showBottomNavigation = true
//    )
//}

