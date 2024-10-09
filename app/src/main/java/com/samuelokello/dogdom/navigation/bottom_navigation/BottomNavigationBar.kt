package com.samuelokello.dogdom.navigation.bottom_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
fun BottomBarNavigation(
    items: List<BottomNavigationItem>,
    onItemClick: (DogdomScreen) -> Unit,
    showBottomBar: Boolean? = false
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    if (showBottomBar == true) {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == selectedItem,
                    onClick = {
                        selectedItem = index
                        onItemClick(DogdomScreen::class.sealedSubclasses.mapNotNull { it.objectInstance }
                            .find { it.route == item.route }
                            ?: DogdomScreen.Home)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.route
                        )
                    },
                    label = {
                        Text(text = item.route)
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