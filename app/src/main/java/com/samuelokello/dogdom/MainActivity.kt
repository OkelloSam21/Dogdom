package com.samuelokello.dogdom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.navigation.DogdomScreen
import com.samuelokello.dogdom.ui.home.HomeViewModel
import com.samuelokello.dogdom.ui.home.HomeViewModelFactory
import com.samuelokello.dogdom.ui.theme.DogdomTheme

class MainActivity : ComponentActivity() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        installSplashScreen().apply {
//            setOnExitAnimationListener { screen ->
//                val zoomX = ObjectAnimator.ofFloat(
//                    screen.iconView,
//                    View.SCALE_X,
//                    0f
//                )
//                zoomX.interpolator = OvershootInterpolator()
//                zoomX.duration = 300L
//                zoomX.doOnEnd { screen.remove() }
//
//
//                val zoomY = ObjectAnimator.ofFloat(
//                    screen.iconView,
//                    View.SCALE_Y,
//                    0f
//                )
//                zoomY.interpolator = OvershootInterpolator()
//                zoomY.duration = 300L
//                zoomY.doOnEnd { screen.remove() }
//
//                zoomX.start()
//                zoomY.start()
//
//            }
//        }
        setContent {
            // A surface container using the 'background' color from the theme
            homeViewModel =
                ViewModelProvider(this, HomeViewModelFactory(DataSource))[HomeViewModel::class.java]
            DogdomTheme {
                DogdomScreen(
                    homeViewModel = homeViewModel,
                    modifier = Modifier

                )
            }
        }
    }
}
