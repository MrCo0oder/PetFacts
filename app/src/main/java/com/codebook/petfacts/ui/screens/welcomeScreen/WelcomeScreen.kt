package com.codebook.petfacts.ui.screens.welcomeScreen

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.applyCanvas
import androidx.hilt.navigation.compose.hiltViewModel
import com.codebook.petfacts.NetworkState
import com.codebook.petfacts.R
import com.codebook.petfacts.Utils.CAT
import com.codebook.petfacts.Utils.DOG
import com.codebook.petfacts.Utils.writeBitmap
import com.codebook.petfacts.data.datasrc.CatFact
import com.codebook.petfacts.data.datasrc.DogFact
import com.codebook.petfacts.data.states.UserInputState
import com.codebook.petfacts.ui.ErrorComponent
import com.codebook.petfacts.ui.FactCard
import com.codebook.petfacts.ui.HeaderText
import com.codebook.petfacts.ui.SubHeader
import java.io.File
import kotlin.math.roundToInt

@Composable
fun WelcomeScreen(
    userInputState: UserInputState,
    viewModel: WelcomeScreenViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current.applicationContext
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
            Spacer(modifier = Modifier.height(20.dp))
            when (userInputState.selectedAnimal) {
                CAT -> {
                    val catFact by viewModel.catFact.collectAsState()
                    when (catFact) {
                        is NetworkState.Loading -> {
                            Box(
                                Modifier
                                    .padding(50.dp)
                                    .fillMaxWidth()
                            )
                            { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
                        }

                        is NetworkState.Success -> {
                            FactCard(
                                fact = (catFact as NetworkState.Success<CatFact>).data.fact.toString(),
                                selectedAnimal = userInputState.selectedAnimal,
                                loadAnotherOne = { viewModel.getCatFact() }) { v, b ->
                                saveZQuote(v, b, CAT, context)

                            }
                        }

                        is NetworkState.Error -> {
                            Spacer(modifier = Modifier.height(20.dp))
                            ErrorComponent {
                                viewModel.getCatFact()
                            }
                        }
                    }
                }

                else -> {
                    val dogFact by viewModel.dogFact.collectAsState()
                    when (dogFact) {
                        is NetworkState.Loading -> {
                            Box(
                                Modifier
                                    .padding(50.dp)
                                    .fillMaxWidth()
                            )
                            {
                                CircularProgressIndicator(
                                    Modifier.align(Alignment.Center),
                                    strokeWidth = 2.dp,
                                    strokeCap = StrokeCap.Butt
                                )
                            }
                        }

                        is NetworkState.Success -> {
                            FactCard(
                                fact = (dogFact as NetworkState.Success<DogFact>).data.facts.toString()
                                    .replace("[", "").replace("]", ""),
                                selectedAnimal = userInputState.selectedAnimal,
                                loadAnotherOne = { viewModel.getDogFact() }) { v, b ->
                                saveZQuote(v, b, DOG, context)
                            }
                        }

                        is NetworkState.Error -> {
                            Spacer(modifier = Modifier.height(20.dp))
                            ErrorComponent {
                                viewModel.getDogFact()
                            }
                        }
                    }
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

private fun saveZQuote(view: View, bounds: Rect?, selectedAnimal: String, context: Context) {
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed(Runnable {
        val bmp = Bitmap
            .createBitmap(
                bounds?.width?.roundToInt()!!,
                bounds.height.roundToInt(),
                Bitmap.Config.ARGB_8888
            )
            .applyCanvas {
                translate(-bounds.left, -bounds.top)
                view.draw(this)
            }
        val file = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM
            ),
            "PetFacts"
        )
        try {
            if (!file.exists() && !file.mkdir()) {
                Toast.makeText(context, "Something  went Error!", Toast.LENGTH_SHORT).show()
            } else {
                val fileName =
                    File(
                        buildString {
                            append(file.absolutePath)
                            append("/screenshot")
                            append(selectedAnimal)
                            append(System.currentTimeMillis())
                            append(".png")
                        }
                    )
                fileName.writeBitmap(
                    bmp,
                    Bitmap.CompressFormat.PNG,
                    100
                )
                Toast.makeText(
                    context,
                    "The Quote saved at ${fileName.absolutePath}",
                    Toast.LENGTH_SHORT
                ).show()

            }
        } catch (e: Exception) {
            Toast.makeText(context, "Something  went Error!", Toast.LENGTH_SHORT).show()
        }
    }, 1000)
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(UserInputState())
}