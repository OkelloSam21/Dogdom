package com.samuelokello.dogdom.navigation

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
    Article,
    CircleDetail
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
    data object CircleDetail : DogdomScreen(Screen.CircleDetail.name)
}