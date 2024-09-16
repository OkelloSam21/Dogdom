package com.samuelokello.dogdom.navigation

import ArticleScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.samuelokello.dogdom.DogdomContainer
import com.samuelokello.dogdom.ui.NotificationScreen
import com.samuelokello.dogdom.ui.ProfileScreen
import com.samuelokello.dogdom.ui.auth.LoginScreen
import com.samuelokello.dogdom.ui.circle.CircleScreen
import com.samuelokello.dogdom.ui.home.HomeScreen
import com.samuelokello.dogdom.ui.message.MessageScreen
import com.samuelokello.dogdom.ui.post.CreatePostScreen
import com.samuelokello.dogdom.ui.release.ReleaseScreen
import com.samuelokello.dogdom.ui.search.SearchScreen

enum class Screen {
    Login,
    Home,
    Circle,
    Release,
    Profile,
    Message,
    Search,
    Notifications,
    CreatePost,
    Article
}

sealed class DogdomScreen(val route: String) {
    data object Login : DogdomScreen(Screen.Login.name)
    data object Home : DogdomScreen(Screen.Home.name)
    data object Circle : DogdomScreen(Screen.Circle.name)
    data object Release : DogdomScreen(Screen.Release.name)
    data object Profile : DogdomScreen(Screen.Profile.name)
    data object Message : DogdomScreen(Screen.Message.name)
    data object Search : DogdomScreen(Screen.Search.name)
    data object Notifications : DogdomScreen(Screen.Notifications.name)
    data object CreatePost : DogdomScreen(Screen.CreatePost.name)
    data object Article : DogdomScreen(Screen.Article.name)
}

@Composable
fun DogdomScreen(
    dogdomContainer: DogdomContainer,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = DogdomScreen::class.sealedSubclasses.mapNotNull { it.objectInstance }.find {
        it.route == backStackEntry.value?.destination?.route
    } ?: DogdomScreen.Home
    val showBottomBar = currentScreen in listOf(
        DogdomScreen.Home,
        DogdomScreen.Circle,
        DogdomScreen.Release,
        DogdomScreen.Message,
        DogdomScreen.Profile
    )
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DogdomScreen.Login.route,
            modifier = modifier
        ) {
            composable(route = DogdomScreen.Login.route) {
                LoginScreen(
                    modifier = modifier.padding(innerPadding),
                    onLoginClick = {
                        navController.navigate(DogdomScreen.Home.route)
                    }
                )
            }
            composable(route = DogdomScreen.Home.route) {
                HomeScreen(
                    modifier = modifier,
                    viewModelFactory = dogdomContainer.homeViewModelFactory,
                    onPostClick = {postId -> navController.navigate("${DogdomScreen.Article.route}/$postId") },
                    showBottomNavigation = showBottomBar,
                    onBottomNavItemClick = { navController.navigate(it.route){ launchSingleTop = true} },
                )
            }
            composable(route = DogdomScreen.Circle.route) {
                CircleScreen(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(innerPadding)
                )
            }
            composable(route = DogdomScreen.Release.route) {

                ReleaseScreen(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(innerPadding)
                )
            }
            composable(route = DogdomScreen.Profile.route) {
                ProfileScreen(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(innerPadding)
                )
            }
            composable(route = DogdomScreen.Message.route) {
                MessageScreen(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(innerPadding)
                )
            }
            composable(route = DogdomScreen.Search.route) {
                SearchScreen(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(innerPadding)
                )

            }
            composable(route = DogdomScreen.Notifications.route) {
                NotificationScreen(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(innerPadding)
                )

            }
            composable(route = DogdomScreen.CreatePost.route) {
                CreatePostScreen(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(innerPadding)
                )
            }
            composable(
                route = "${DogdomScreen.Article.route}/{postId}",
                arguments = listOf(navArgument("postId") { type = NavType.IntType })
                ) { backStackEntry ->
                val postId = backStackEntry.arguments?.getInt("postId") ?: -1
                ArticleScreen(
                    modifier = Modifier.padding(innerPadding),
                    postId = postId,
                    viewModelFactory = dogdomContainer.articleViewModelFactory,
                    onBackClick = {navController.popBackStack(DogdomScreen.Home.route,inclusive = false)}
                )
            }
        }

    }
}
