package com.codebook.petfacts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codebook.petfacts.R
import com.codebook.petfacts.Utils.CAT
import com.codebook.petfacts.data.UserInputState
import com.codebook.petfacts.ui.HeaderText
import com.codebook.petfacts.ui.SubHeader

@Composable
fun WelcomeScreen(userInputState: UserInputState) {
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .verticalScroll(state = scrollState),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            HeaderText(
                text = "Welcome ${userInputState.name}  🥳",
                image = if (userInputState.selectedAnimal == CAT) R.drawable.cat else R.drawable.dog
            )
            Spacer(modifier = Modifier.height(10.dp))
            SubHeader(text = "Thank You For Sharing Your Data !", textSize = 22.sp)
            Spacer(modifier = Modifier.height(60.dp))
            SubHeader(
                text = "You are a ${userType(userInputState.selectedAnimal)}",
                textSize = 24.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(1.dp), colors = CardDefaults.cardColors(
                    Color(
                        0xFFEDE7F6
                    )
                )
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(18.dp, 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_quotes_top),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SubHeader(
                        text = "This is a Quote About ${userInputState.selectedAnimal}s",
                        textSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(180f),
                        painter = painterResource(id = R.drawable.ic_quotes_top),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

fun userType(state: String): String =
    when (state) {
        CAT -> {
            "Cat Lover  😺."
        }

        else -> {
            "Dog Lover  🐶."
        }
    }

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(UserInputState())
}