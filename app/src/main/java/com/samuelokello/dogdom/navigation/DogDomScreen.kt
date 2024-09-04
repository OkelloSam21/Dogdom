package com.samuelokello.dogdom.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samuelokello.dogdom.ui.auth.LoginScreen
import com.samuelokello.dogdom.ui.home.HomeScreen
import com.samuelokello.dogdom.ui.home.HomeViewModel

enum class DogdomScreen {
    Login,
    Home,
    Profile,
    Search,
    Notifications,
    CreatePost
}

@Composable
fun DogdomScreen(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = DogdomScreen.valueOf(
        backStackEntry?.destination?.route ?: DogdomScreen.Home.name
    )
    Scaffold { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = DogdomScreen.Login.name,
            modifier = modifier
        ) {
            composable(route = DogdomScreen.Login.name) {
                LoginScreen(
                    modifier = modifier.padding(paddingValues),
                    onLoginClick = {
                        navController.navigate(DogdomScreen.Home.name)
                    }
                )
            }

            composable(route = DogdomScreen.Home.name) {
                HomeScreen(
                    viewModel = homeViewModel,
                    modifier = modifier
                        .safeDrawingPadding()
                        .padding(paddingValues),
                )
            }

        }

    }
}
