package com.samuelokello.dogdom.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.ui.auth.LoginScreen
import com.samuelokello.dogdom.ui.home.HomeScreen
import com.samuelokello.dogdom.ui.home.HomeViewModel

enum class DogdomScreen() {
    Login,
    Home,
    Profile,
    Search,
    Notifications,
    CreatePost
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDomAppBar(
    modifier: Modifier = Modifier,
    hasTitle: Boolean,
    currentScreen: String,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    actions: @Composable (RowScope) -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            if (hasTitle) {
                Text(
                    text = currentScreen,
                    style = MaterialTheme.typography.displaySmall,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        },
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = actions,
    )
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
    Scaffold(
        topBar = {
            DogDomAppBar(
                modifier = modifier,
                hasTitle = false,
                currentScreen = currentScreen.name,
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                actions = {}
            )
        },
        bottomBar = {

        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = DogdomScreen.Login.name,
            modifier = modifier
        ) {
            composable(
                route = DogdomScreen.Login.name,
                enterTransition = {
                    scaleIntoContainer()
                },
                exitTransition = {
                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
                },
                popEnterTransition = {
                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
                },
                popExitTransition = {
                    scaleOutOfContainer()
                }
            ) {
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
                    modifier = modifier.padding(paddingValues),
                )
            }

        }

    }
}

enum class ScaleTransitionDirection {
    INWARDS, OUTWARDS
}

fun scaleIntoContainer(
    direction: ScaleTransitionDirection = ScaleTransitionDirection.INWARDS,
    initialScale: Float = if (direction == ScaleTransitionDirection.OUTWARDS) 0.9f else 1.1f
): EnterTransition{
    return scaleIn(
        animationSpec = tween(220, delayMillis = 90),
        initialScale = initialScale
    ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
}

fun scaleOutOfContainer(
    direction: ScaleTransitionDirection = ScaleTransitionDirection.OUTWARDS,
    targetScale: Float = if (direction == ScaleTransitionDirection.INWARDS) 0.9f else 1.1f
): ExitTransition {
    return scaleOut(
        animationSpec = tween(
            durationMillis = 220,
            delayMillis = 90
        ), targetScale = targetScale
    ) + fadeOut(tween(delayMillis = 90))
}