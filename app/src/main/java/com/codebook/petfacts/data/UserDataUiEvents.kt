package com.codebook.petfacts.data


sealed class UserDataUiEvents {
    data class UserNameEntered(val name: String) : UserDataUiEvents()
    data class SelectedAnimal(val selectedAnimal: String) : UserDataUiEvents()
}