package com.codebook.petfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codebook.petfacts.ui.screens.PetFactsNavigationGraph
import com.codebook.petfacts.ui.theme.PetFactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            PetFactsTheme {
                FunFactApp()
            }
        }
    }

    @Composable
    fun FunFactApp() {
      PetFactsNavigationGraph()
    }
}
