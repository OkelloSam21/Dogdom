package com.samuelokello.dogdom.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samuelokello.dogdom.navigation.bottom_navigation.BottomNavigationBar
import com.samuelokello.dogdom.ui.NotificationScreen
import com.samuelokello.dogdom.ui.ProfileScreen
import com.samuelokello.dogdom.ui.release.ReleaseScreen
import com.samuelokello.dogdom.ui.auth.LoginScreen
import com.samuelokello.dogdom.ui.circle.CircleScreen
import com.samuelokello.dogdom.ui.home.HomeScreen
import com.samuelokello.dogdom.ui.home.HomeViewModel
import com.samuelokello.dogdom.ui.message.MessageScreen
import com.samuelokello.dogdom.ui.post.CreatePostScreen
import com.samuelokello.dogdom.ui.search.SearchScreen

enum class DogdomScreen {
    Login,
    Home,
    Circle,
    Release,
    Profile,
    Message,
    Search,
    Notifications,
    CreatePost
}

@Composable
fun DogdomScreen(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    var showBottomBar by remember { mutableStateOf(false) }
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = DogdomScreen.valueOf(
        backStackEntry.value?.destination?.route ?: DogdomScreen.Home.name
    )
    Scaffold { paddingValues ->
        BottomNavigationBar( // Place BottomNavigationBar outside NavHost
            modifier = modifier.padding(paddingValues),
            showBottomBar = currentScreen in listOf(
                DogdomScreen.Home,
                DogdomScreen.Circle,
                DogdomScreen.Release,
                DogdomScreen.Message,
                DogdomScreen.Profile
            ),
            onItemClick = { screen ->
                navController.navigate(screen.name)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = DogdomScreen.Login.name,
                modifier = modifier
            ) {
                composable(route = DogdomScreen.Login.name) {
                    LoginScreen(
                        modifier = modifier.padding(innerPadding),
                        onLoginClick = {
                            navController.navigate(DogdomScreen.Home.name)
                        }
                    )
                }
                composable(route = DogdomScreen.Home.name) {
                    HomeScreen(
                        viewModel = homeViewModel,
                        showBottomBar = !showBottomBar,
                        modifier = modifier
                            .safeDrawingPadding()
                            .padding(innerPadding),
                    )
                }
                composable(route = DogdomScreen.Circle.name) {
                    CircleScreen(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(innerPadding)
                    )
                }
                composable(route = DogdomScreen.Release.name) {

                    ReleaseScreen(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(innerPadding)
                    )
                }
                composable(route = DogdomScreen.Profile.name) {
                    ProfileScreen(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(innerPadding)
                    )
                }
                composable(route = DogdomScreen.Message.name) {
                    MessageScreen(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(innerPadding)
                    )
                }
                composable(route = DogdomScreen.Search.name) {
                    SearchScreen(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(innerPadding)
                    )

                }
                composable(route = DogdomScreen.Notifications.name) {
                    NotificationScreen(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(innerPadding)
                    )

                }
                composable(route = DogdomScreen.CreatePost.name) {
                    CreatePostScreen(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(innerPadding)
                    )
                }

            }
        }
    }
}
