package com.codebook.petfacts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.petfacts.ui.HeaderText
import com.codebook.petfacts.ui.SubHeader

@Composable
fun UserInputScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            HeaderText("Hello There! 😊")
            SubHeader(text = "Let's learn about you !", textSize = 24.sp)
            Spacer(modifier = Modifier.height(20.dp))
            SubHeader(
                text = "This app will prepare a details page based on input provided by you !",
                textSize = 18.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Preview
@Composable
fun UserInputScreenPreview() {
    UserInputScreen(rememberNavController())
}