package com.samuelokello.dogdom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.navigation.DogdomScreen
import com.samuelokello.dogdom.ui.article.ArticleViewModel
import com.samuelokello.dogdom.ui.home.HomeViewModel
import com.samuelokello.dogdom.ui.home.HomeViewModelFactory
import com.samuelokello.dogdom.ui.theme.DogdomTheme

class MainActivity : ComponentActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // A surface container using the 'background' color from the theme
            homeViewModel =
                ViewModelProvider(this, HomeViewModelFactory(DataSource))[HomeViewModel::class.java]
            articleViewModel = ViewModelProvider(this)[ArticleViewModel::class.java]
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                DogdomTheme {
                    DogdomScreen(
                        homeViewModel = homeViewModel,
                        articleViewModel = articleViewModel,
                        modifier = Modifier
                    )
                }
            }

        }
    }
}
