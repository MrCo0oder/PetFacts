package com.codebook.petfacts.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codebook.petfacts.data.UserInputState
import com.codebook.petfacts.ui.UserInputViewModel
import com.codebook.petfacts.ui.screens.Routes.SELECTED_ANIMAL
import com.codebook.petfacts.ui.screens.Routes.USER_INPUT_SCREEN
import com.codebook.petfacts.ui.screens.Routes.USER_NAME
import com.codebook.petfacts.ui.screens.Routes.WELCOME_SCREEN

@Composable
fun PetFactsNavigationGraph(userInputViewModel: UserInputViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = USER_INPUT_SCREEN) {
        composable(
            "${WELCOME_SCREEN}/{${USER_NAME}}/{${SELECTED_ANIMAL}}",
            arguments = listOf(
                navArgument(name = USER_NAME) {
                    type = NavType.StringType
                },
                navArgument(name = SELECTED_ANIMAL) {
                    type = NavType.StringType
                }
            ),
        ) {
            it.arguments?.apply {
                WelcomeScreen(
                    UserInputState(
                        name = getString(USER_NAME)!!,
                        selectedAnimal = getString(SELECTED_ANIMAL)!!
                    )
                )
            }

        }
        composable(USER_INPUT_SCREEN) {
            UserInputScreen(navController, userInputViewModel) {
                navController.navigate(buildString {
                    append(WELCOME_SCREEN)
                    append("/")
                    append(it.first)
                    append("/")
                    append(it.second)
                })
            }

        }
    }
}