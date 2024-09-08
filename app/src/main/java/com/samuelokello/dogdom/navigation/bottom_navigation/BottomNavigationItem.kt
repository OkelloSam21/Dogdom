package com.samuelokello.dogdom.navigation.bottom_navigation

import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.navigation.DogdomScreen


data class BottomNavigationItem(
    val label: String = "",
    val icon: Int = 0,
    val route: String = ""
) {
    // get list of the navigation items
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem("Home", R.drawable.home, DogdomScreen.Home.name), // home
            BottomNavigationItem("Circle", R.drawable.circle, DogdomScreen.Circle.name), // Circle
            BottomNavigationItem("Release", R.drawable.release, DogdomScreen.Release.name), // Release
            BottomNavigationItem("Message", R.drawable.comments, DogdomScreen.Message.name), // Message
            BottomNavigationItem("User", R.drawable.user, DogdomScreen.Profile.name), // User
        )
    }
}