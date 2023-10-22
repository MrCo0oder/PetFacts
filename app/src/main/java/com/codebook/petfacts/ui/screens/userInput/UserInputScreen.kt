package com.codebook.petfacts.ui.screens.userInput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codebook.petfacts.R
import com.codebook.petfacts.Utils
import com.codebook.petfacts.data.states.UserDataUiEvents
import com.codebook.petfacts.ui.HeaderText
import com.codebook.petfacts.ui.SelectableCard
import com.codebook.petfacts.ui.SubHeader
import com.codebook.petfacts.ui.TextFieldComponent

@Composable
fun UserInputScreen(
    userInputViewModel: UserInputViewModel,
    showWelcomeScreen: (Pair<String, String>) -> Unit
) {
    val localFocusManager = LocalFocusManager.current
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
                .verticalScroll(
                    state = scrollState
                )
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            HeaderText("Hello There! 😊")
            SubHeader(text = "Let's learn about you !", textSize = 24.sp)
            Spacer(modifier = Modifier.height(10.dp))
            SubHeader(
                text = "This app will prepare a details page based on input provided by you !",
                textSize = 18.sp
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextFieldComponent("Name", 16.sp, default = userInputViewModel.uiState.value.name) {
                userInputViewModel.onEvent(UserDataUiEvents.UserNameEntered(it))
            }
            Spacer(modifier = Modifier.height(20.dp))
            SubHeader(
                text = "What do you like ?",
                textSize = 18.sp
            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SelectableCard(
                    R.drawable.cat,
                    userInputViewModel.uiState.value.selectedAnimal == Utils.CAT
                ) {
                    localFocusManager.clearFocus()
                    userInputViewModel.onEvent(UserDataUiEvents.SelectedAnimal(it))
                }
                SelectableCard(
                    R.drawable.dog,
                    userInputViewModel.uiState.value.selectedAnimal == Utils.DOG
                ) {
                    localFocusManager.clearFocus()
                    userInputViewModel.onEvent(UserDataUiEvents.SelectedAnimal(it))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (userInputViewModel.isValidScreen())
                Button(onClick = {
                    localFocusManager.clearFocus()
                    showWelcomeScreen(
                        Pair(
                            userInputViewModel.uiState.value.name,
                            userInputViewModel.uiState.value.selectedAnimal
                        )
                    )
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Let's Cook  🚀", fontWeight = FontWeight.Light)
                }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
fun UserInputScreenPreview() {
    UserInputScreen(UserInputViewModel()) {
    }
}