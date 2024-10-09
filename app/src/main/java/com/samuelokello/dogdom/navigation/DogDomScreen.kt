package com.samuelokello.dogdom.navigation

import ArticleScreen
import androidx.compose.foundation.layout.fillMaxSize
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
import com.samuelokello.dogdom.navigation.bottom_navigation.BottomBarNavigation
import com.samuelokello.dogdom.navigation.bottom_navigation.BottomNavigationItem
import com.samuelokello.dogdom.ui.NotificationScreen
import com.samuelokello.dogdom.ui.ProfileScreen
import com.samuelokello.dogdom.ui.auth.LoginScreen
import com.samuelokello.dogdom.ui.circle.CircleScreen
import com.samuelokello.dogdom.ui.circle.circle_details.CircleDetailScreen
import com.samuelokello.dogdom.ui.home.HomeScreen
import com.samuelokello.dogdom.ui.message.MessageScreen
import com.samuelokello.dogdom.ui.post.CreatePostScreen
import com.samuelokello.dogdom.ui.release.ReleaseScreen
import com.samuelokello.dogdom.ui.search.SearchScreen


@Composable
fun DogdomScreen(
    dogdomContainer: DogdomContainer,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = DogdomScreen::class.sealedSubclasses.mapNotNull { it.objectInstance }.find {
        it.route == backStackEntry.value?.destination?.route
    } ?: DogdomScreen.Login


    val showBottomBar = currentScreen in listOf(
        DogdomScreen.Home,
        DogdomScreen.Circle,
        DogdomScreen.Release,
        DogdomScreen.Message,
        DogdomScreen.Profile
    )

    Scaffold(
        bottomBar = {
            BottomBarNavigation(
                items = BottomNavigationItem().bottomNavigationItems(),
                onItemClick = {
                    navController.navigate(it.route)
//                    {
//                        launchSingleTop = true
//                        popUpTo(navController.graph.startDestinationId) {
//                            saveState = true
//                        }
//                    }
                },
                showBottomBar = showBottomBar
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DogdomScreen.Login.route,
        ) {
            composable(route = DogdomScreen.Login.route) {
                LoginScreen(
                    modifier = modifier.padding(innerPadding),
                    onLoginClick = {
                        navController.navigate(DogdomScreen.Home.route) {
                            popUpTo(DogdomScreen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(route = DogdomScreen.Home.route) {
                HomeScreen(
                    modifier = modifier,
                    viewModelFactory = dogdomContainer.homeViewModelFactory,
                    onPostClick = { postId -> navController.navigate("${DogdomScreen.Article.route}/$postId") },
                )
            }
            composable(route = DogdomScreen.Circle.route) {
                CircleScreen(
                    modifier = modifier,
                    onCircleClick = { circleId -> navController.navigate("${DogdomScreen.CircleDetail.route}/${circleId}") }
                )
            }
            composable(route = DogdomScreen.Release.route) {
                ReleaseScreen(
                    modifier = Modifier
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
                    onBackClick = {
                        navController.popBackStack(
                            DogdomScreen.Home.route,
                            inclusive = false
                        )
                    }
                )
            }
            composable(
                route = "${DogdomScreen.CircleDetail.route}/{circleId}",
                arguments = listOf(navArgument("circleId") { type = NavType.IntType })
            ) { backStackEntry ->
                val circleId = backStackEntry.arguments?.getInt("circleId") ?: -1
                CircleDetailScreen(
                    id = circleId,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

//@Composable
//fun DogdomScreen(
//    dogdomContainer: DogdomContainer,
//    modifier: Modifier = Modifier,
//) {
//    val navController = rememberNavController()
//
//    val backStackEntry = navController.currentBackStackEntryAsState()
//    val currentScreen = DogdomScreen::class.sealedSubclasses.mapNotNull { it.objectInstance }.find {
//        it.route == backStackEntry.value?.destination?.route
//    } ?: DogdomScreen.Login
//    val showBottomBar = currentScreen in listOf(
//        DogdomScreen.Home,
//        DogdomScreen.Circle,
//        DogdomScreen.Release,
//        DogdomScreen.Message,
//        DogdomScreen.Profile
//    )
//    Scaffold (
//        bottomBar =   {
//            if(showBottomBar) {
//                BottomNavigationBar(
//                    showBottomBar = showBottomBar,
//                    onItemClick = {
//                        navController.navigate(it.route) {
//                            launchSingleTop = true
//                            popUpTo(navController.graph.startDestinationId) {
//                                saveState = true
//                            }
//                        }
//                    }
//                )
//            }
//        }
//    ){ innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = DogdomScreen.Login.route,
//            modifier = modifier.padding(innerPadding)
//        ) {
//            composable(route = DogdomScreen.Login.route) {
//                LoginScreen(
//                    modifier = modifier.padding(innerPadding),
//                    onLoginClick = {
//                        navController.navigate(DogdomScreen.Home.route) {
//
//                        }
//                    }
//                )
//            }
//            composable(route = DogdomScreen.Home.route) {
//                HomeScreen(
//                    modifier = modifier,
//                    viewModelFactory = dogdomContainer.homeViewModelFactory,
//                    onPostClick = { postId -> navController.navigate("${DogdomScreen.Article.route}/$postId") },
//                )
//            }
//            composable(route = DogdomScreen.Circle.route) {
//                CircleScreen(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    onCircleClick = { circleId -> navController.navigate("${DogdomScreen.CircleDetail.route}/${circleId}") }
//                )
//            }
//            composable(route = DogdomScreen.Release.route) {
//                ReleaseScreen(
//                    modifier = Modifier
//                        .safeDrawingPadding()
//                        .padding(innerPadding)
//                )
//            }
//            composable(route = DogdomScreen.Profile.route) {
//                ProfileScreen(
//                    modifier = Modifier
//                        .safeDrawingPadding()
//                        .padding(innerPadding)
//                )
//            }
//            composable(route = DogdomScreen.Message.route) {
//                MessageScreen(
//                    modifier = Modifier
//                        .safeDrawingPadding()
//                        .padding(innerPadding)
//                )
//            }
//            composable(route = DogdomScreen.Search.route) {
//                SearchScreen(
//                    modifier = Modifier
//                        .safeDrawingPadding()
//                        .padding(innerPadding)
//                )
//            }
//            composable(route = DogdomScreen.Notifications.route) {
//                NotificationScreen(
//                    modifier = Modifier
//                        .safeDrawingPadding()
//                        .padding(innerPadding)
//                )
//            }
//            composable(route = DogdomScreen.CreatePost.route) {
//                CreatePostScreen(
//                    modifier = Modifier
//                        .safeDrawingPadding()
//                        .padding(innerPadding)
//                )
//            }
//            composable(
//                route = "${DogdomScreen.Article.route}/{postId}",
//                arguments = listOf(navArgument("postId") { type = NavType.IntType })
//            ) { backStackEntry ->
//                val postId = backStackEntry.arguments?.getInt("postId") ?: -1
//                ArticleScreen(
//                    modifier = Modifier.padding(innerPadding),
//                    postId = postId,
//                    viewModelFactory = dogdomContainer.articleViewModelFactory,
//                    onBackClick = {
//                        navController.popBackStack(
//                            DogdomScreen.Home.route,
//                            inclusive = false
//                        )
//                    }
//                )
//            }
//            composable(
//                route = "${DogdomScreen.CircleDetail.route}/{circleId}",
//                arguments = listOf(navArgument("circleId") { type = NavType.IntType })
//                ) { backStackEntry ->
//                val circleId = backStackEntry.arguments?.getInt("circleId") ?: -1
//                CircleDetailScreen(
//                    id = circleId,
//                    modifier = Modifier.padding(innerPadding)
//                )
//
//            }
//        }
//
//    }
//}
