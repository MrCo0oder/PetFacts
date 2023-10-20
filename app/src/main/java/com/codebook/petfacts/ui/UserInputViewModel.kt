package com.codebook.petfacts.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codebook.petfacts.data.UserDataUiEvents
import com.codebook.petfacts.data.UserInputState

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

    fun isValidScreen(): Boolean {
        return !uiState.value.name.isNullOrEmpty() && !uiState.value.selectedAnimal.isNullOrEmpty()
    }

}
