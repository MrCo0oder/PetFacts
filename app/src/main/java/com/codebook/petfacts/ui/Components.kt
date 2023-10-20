package com.codebook.petfacts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codebook.petfacts.R

@Composable
fun HeaderText(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .size(65.dp),
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = "", contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderTextPreview() {
    HeaderText("Hi There! 😊")
}

@Composable
fun SubHeader(text: String, textSize: TextUnit, color: Color = Color.Black) {

    Text(
        text = text,
        color = color,
        fontSize = textSize,
        fontWeight = FontWeight.Light,
    )
}

@Preview(showBackground = true)
@Composable
fun SubHeaderPreview() {
    SubHeader("Hi There! 😊", 24.sp)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    label: String,
    labelSize: TextUnit,
    labelColor: Color = Color.Black,
    onValueChange: (String) -> Unit
) {
    var value by remember {
        mutableStateOf("")
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
            value = value,
            onValueChange = {
                value = it
                onValueChange(it)
            },
            placeholder = {
                Text(text = "Enter your name", fontSize = 18.sp)
            },
            textStyle = TextStyle.Default.copy(fontSize = 24.sp),
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
fun TextFieldComponentPreview() {
    TextFieldComponent("Name", 18.sp) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableCard(image: Int, isSelected: Boolean, onClick: (String) -> Unit) {
    Card(
        onClick = {
            val selectedAnimal = if (image == R.drawable.cat) "Cat" else "Dog"
            onClick(selectedAnimal)
        },
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
fun SelectableCardPreview() {
    SelectableCard(R.drawable.cat, true) {

    }
}