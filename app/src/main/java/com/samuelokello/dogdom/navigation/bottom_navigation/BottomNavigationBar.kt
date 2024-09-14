package com.samuelokello.dogdom.navigation.bottom_navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.samuelokello.dogdom.navigation.DogdomScreen
import com.samuelokello.dogdom.ui.theme.CustomOrange

@Composable
fun BottomNavigationBar(
    showBottomBar: Boolean = false,
    onItemClick: (DogdomScreen) -> Unit,
    content: @Composable (innerPadding: PaddingValues) -> Unit = {}
) {
    var navigationSelectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.White
                ) {
                    BottomNavigationItem().bottomNavigationItems()
                        .forEachIndexed { index, navigationItem ->
                            NavigationBarItem(
                                selected = index == navigationSelectedItem,
                                onClick = {
                                    navigationSelectedItem = index
                                    onItemClick(DogdomScreen::class.sealedSubclasses.mapNotNull { it.objectInstance }
                                        .find { it.route == navigationItem.route }
                                        ?: DogdomScreen.Home)
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = navigationItem.icon),
                                        contentDescription = navigationItem.route
                                    )
                                },
                                label = {
                                    Text(text = navigationItem.route)
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color.White,
                                    selectedTextColor = CustomOrange,
                                    indicatorColor = CustomOrange.copy(alpha = 0.8f)
                                )
                            )
                        }
                }
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}