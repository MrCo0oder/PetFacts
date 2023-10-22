package com.codebook.petfacts.ui.screens.welcomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codebook.petfacts.NetworkState
import com.codebook.petfacts.R
import com.codebook.petfacts.Utils.CAT
import com.codebook.petfacts.Utils.DOG
import com.codebook.petfacts.data.datasrc.CatFact
import com.codebook.petfacts.data.datasrc.DogFact
import com.codebook.petfacts.data.states.UserInputState
import com.codebook.petfacts.ui.HeaderText
import com.codebook.petfacts.ui.SubHeader

@Composable
fun WelcomeScreen(
    userInputState: UserInputState,
    viewModel: WelcomeScreenViewModel = hiltViewModel()
) {

    Surface(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        when (userInputState.selectedAnimal) {
            CAT -> {
                val catFact by viewModel.catFact.collectAsState()
                when (catFact) {
                    is NetworkState.Loading -> {
                        Box(Modifier.padding(50.dp))
                        { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
                    }

                    is NetworkState.Success -> {
                        Content(
                            userInputState = userInputState,
                            (catFact as NetworkState.Success<CatFact>).data.fact.toString()
                        ) {
                            viewModel.getCatFact()
                        }
                    }

                    is NetworkState.Error -> {
                        Text(text = (catFact as NetworkState.Error<CatFact>).error.toString())
                    }
                }

            }

            DOG -> {
                val catFact by viewModel.dogFact.collectAsState()
                when (catFact) {
                    is NetworkState.Loading -> {
                        Box(Modifier.padding(50.dp))
                        { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
                    }

                    is NetworkState.Success -> {
                        Content(
                            userInputState = userInputState,
                            (catFact as NetworkState.Success<DogFact>).data.facts.toString()
                                .replace("[", "").replace("]", "")
                        ) {
                            viewModel.getDogFact()
                        }
                    }

                    is NetworkState.Error -> {
                        Text(text = (catFact as NetworkState.Error<DogFact>).error.toString())
                    }
                }
            }

            else -> {
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(userInputState: UserInputState, fact: String, function: () -> Unit) {
    val scrollState = rememberScrollState()

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
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(
                Color(
                    0xFFEDE7F6
                )
            )
        ) {
            Box {
                Image(
                    modifier = Modifier
//                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp).fillMaxSize()
                        .alpha(0.15F)
                        .align(Alignment.BottomCenter),
                    painter = painterResource(id = if (userInputState.selectedAnimal == CAT) R.drawable.cat else R.drawable.dog),
                    contentDescription = null
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(18.dp, 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_quotes_top),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            onClick = {
                                function()
                            },
                            modifier = Modifier
                                .size(24.dp),
                            elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                            ) {
                                Image(
                                    imageVector = Icons.Default.Refresh,
                                    contentScale = ContentScale.Inside,
                                    contentDescription = "Animal Image"
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    SubHeader(
                        text = fact, textSize = 24.sp,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(180f),
                        painter = painterResource(id = R.drawable.ic_quotes_top),
                        contentDescription = ""
                    )
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