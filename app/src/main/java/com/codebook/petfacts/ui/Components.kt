package com.codebook.petfacts.ui

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codebook.petfacts.R
import com.codebook.petfacts.Utils
import com.codebook.petfacts.Utils.CAT

@Composable
fun HeaderText(text: String, image: Int = R.drawable.image, color: Color = Color(0xFF000000)) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            color = color,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium, overflow = TextOverflow.Ellipsis, maxLines = 1,
        )
        Image(
            modifier = Modifier
                .size(50.dp),
            painter = painterResource(id = image),
            contentDescription = "", contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderTextPreview() {
    HeaderText("Hi There! 😊")
}

@Composable
fun SubHeader(text: String, textSize: TextUnit, color: Color = Color(0xFF000000)) {

    Text(
        text = text,
        color = color,
        fontSize = textSize,
        fontWeight = FontWeight.Light,
    )
}

@Preview(showBackground = true)
@Composable
private fun SubHeaderPreview() {
    SubHeader("Hi There! 😊", 24.sp)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    label: String,
    labelSize: TextUnit,
    labelColor: Color = Color(0xFF000000),
    default: String,
    onValueChange: (String) -> Unit
) {
    var value by remember {
        mutableStateOf(default)
    }
    val localFocusManager = LocalFocusManager.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = labelColor,
            fontSize = labelSize,
            fontWeight = FontWeight.Normal,
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value.trim(),
            onValueChange = {
                value = it.trim()
                onValueChange(it.trim())
            },
            placeholder = {
                Text(text = "Enter your name", fontSize = 18.sp)
            },
            textStyle = TextStyle.Default.copy(fontSize = 24.sp, color = Color(0xFF000000)),
            singleLine = true,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Gray
//            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldComponentPreview() {
    TextFieldComponent("Name", 18.sp, default = "") {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableCard(
    image: Int,
    isSelected: Boolean,
    color: Color = Color(0xFFECFBC7),
    onClick: (String) -> Unit
) {
    Card(
        onClick = {
            val selectedAnimal = if (image == R.drawable.cat) CAT else Utils.DOG
            onClick(selectedAnimal)
        }, colors = CardDefaults.cardColors(color),
        modifier = Modifier
            .size(180.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(9),
                    color = if (isSelected) Color.Green else Color.Transparent
                ),
        ) {
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize(),
                painter = painterResource(id = image),
                contentScale = ContentScale.Inside,
                contentDescription = "Animal Image"
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableCardPreview() {
    SelectableCard(R.drawable.cat, true) {

    }
}

@Composable
fun FactCard(
    fact: String,
    selectedAnimal: String,
    loadAnotherOne: () -> Unit,
    saveZQuote: (View, Rect?) -> Unit,
) {
    var capturingViewBounds by remember { mutableStateOf<Rect?>(null) }
    val view = LocalView.current
    val bounds = capturingViewBounds
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .onGloballyPositioned {
                    capturingViewBounds = it.boundsInRoot()
                }
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.run {
                if (selectedAnimal == CAT) cardColors(
                    Color(0xFFECFBC7),
                    contentColor = Color.Black
                )
                else
                    cardColors(
                        Color(
                            0xFFEDE7F6
                        ), contentColor = Color.Black
                    )
            }

        ) {

            Box {
                Image(
                    modifier = Modifier
                        .matchParentSize()
                        .align(Alignment.BottomCenter)
                        .padding(start = 16.dp, end = 16.dp)
                        .alpha(0.15F),
                    painter = painterResource(id = if (selectedAnimal == CAT) R.drawable.cat else R.drawable.dog),
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
                        IconButton(onClick = { loadAnotherOne() }) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = fact,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF000000)
                        , textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(180f)
                            .clickable {

                            },
                        painter = painterResource(id = R.drawable.ic_quotes_top),
                        contentDescription = ""
                    )
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { saveZQuote(view, bounds) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = "Save this Quote as an Image."
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FactCardPreview() {
    FactCard(
        "Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla ",
        CAT, {}
    ) { _, _ ->

    }
}

@Composable
fun ErrorComponent(retry: () -> Unit) {
    Column(
        Modifier
            .wrapContentSize()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_image),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = "Oh NO!",
            style = TextStyle(
                fontSize = 30.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(700),
                color = Color.DarkGray,
                letterSpacing = 0.6.sp,
            )
        )
        Text(
            text = "May be bigfoot has broken this page.\n Try Again! ",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF666666),
                textAlign = TextAlign.Center,
                letterSpacing = 0.28.sp,
            )
        )
        Button(
            onClick = { retry() }, modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Retry !")
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    ErrorComponent {

    }
}

