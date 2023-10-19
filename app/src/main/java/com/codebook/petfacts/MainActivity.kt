package com.codebook.petfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codebook.petfacts.ui.screens.PetFactsNavigationGraph
import com.codebook.petfacts.ui.screens.Routes
import com.codebook.petfacts.ui.screens.UserInputScreen
import com.codebook.petfacts.ui.screens.WelcomeScreen
import com.codebook.petfacts.ui.theme.PetFactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
