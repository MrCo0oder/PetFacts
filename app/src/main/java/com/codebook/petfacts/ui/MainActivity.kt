package com.codebook.petfacts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codebook.petfacts.ui.screens.PetFactsNavigationGraph
import com.codebook.petfacts.ui.theme.PetFactsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            PetFactsTheme(darkTheme = false, dynamicColor = false) {
                FunFactApp()
            }
        }
    }

    @Composable
    fun FunFactApp() {
      PetFactsNavigationGraph()
    }
}
