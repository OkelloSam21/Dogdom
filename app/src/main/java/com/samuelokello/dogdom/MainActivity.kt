package com.samuelokello.dogdom

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.samuelokello.dogdom.ui.theme.DogdomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
             val zoomX = ObjectAnimator.ofFloat(
                 screen.iconView,
                 View.SCALE_X,
                 0f
             )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }


                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()

            }
        }
        setContent {
            DogdomTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(text = "Hello World!", modifier = modifier)
}

