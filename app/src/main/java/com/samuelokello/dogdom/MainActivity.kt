package com.samuelokello.dogdom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.samuelokello.dogdom.navigation.DogdomScreen
import com.samuelokello.dogdom.ui.theme.DogdomTheme

class MainActivity : ComponentActivity() {

    private lateinit var appContainer: DogdomContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        appContainer = DogdomContainer(applicationContext)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                DogdomTheme {
                    DogdomScreen(
                        dogdomContainer = appContainer,
                        modifier = Modifier
                    )
                }
            }

        }
    }
}
