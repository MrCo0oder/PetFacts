package com.codebook.petfacts.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codebook.petfacts.data.UserDataUiEvents
import com.codebook.petfacts.data.UserInputState

class UserInputViewModel : ViewModel() {
    var uiState = mutableStateOf(UserInputState())

    fun onEvent(event: UserDataUiEvents) {
        when (event) {
            is UserDataUiEvents.UserNameEntered -> {}
            is UserDataUiEvents.SelectedAnimal -> {

            }
        }
    }

}
