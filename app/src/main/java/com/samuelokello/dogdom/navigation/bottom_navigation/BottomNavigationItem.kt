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
            BottomNavigationItem("Home", R.drawable.home, DogdomScreen.Home.route), // home
            BottomNavigationItem("Circle", R.drawable.circle, DogdomScreen.Circle.route), // Circle
            BottomNavigationItem("Release", R.drawable.release, DogdomScreen.Release.route), // Release
            BottomNavigationItem("Message", R.drawable.comments, DogdomScreen.Message.route), // Message
            BottomNavigationItem("User", R.drawable.user, DogdomScreen.Profile.route), // User
        )
    }
}