package com.codebook.petfacts.ui.screens.userInput

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codebook.petfacts.data.states.UserDataUiEvents
import com.codebook.petfacts.data.states.UserInputState

class UserInputViewModel : ViewModel() {
    var uiState = mutableStateOf(UserInputState())

    fun onEvent(event: UserDataUiEvents) {
        when (event) {
            is UserDataUiEvents.UserNameEntered -> {
                uiState.value = uiState.value.copy(name = event.name)
            }

            is UserDataUiEvents.SelectedAnimal -> {
                uiState.value = uiState.value.copy(selectedAnimal = event.selectedAnimal)
            }
        }
    }

    fun isValidScreen(): Boolean = uiState.value.name.isNotEmpty() && uiState.value.selectedAnimal.isNotEmpty()
}
