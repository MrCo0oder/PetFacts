package com.codebook.petfacts.data.states


sealed class UserDataUiEvents {
    data class UserNameEntered(val name: String) : UserDataUiEvents()
    data class SelectedAnimal(val selectedAnimal: String) : UserDataUiEvents()
}