package com.codebook.petfacts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
                .size(50.dp)
                .clip(CircleShape) // clip to the circle shape
                .border(2.dp, Color.Gray, CircleShape),
            painter = painterResource(id = R.drawable.image),
            contentDescription = ""
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


@Composable
fun TextFieldComponent(text: String, textSize: TextUnit, color: Color = Color.Black) {

    Text(
        text = text,
        color = color,
        fontSize = textSize,
        fontWeight = FontWeight.Light,
    )
//    OutlinedTextField(value = "", onValueChange = ){}
}

@Preview(showBackground = true)
@Composable
fun TextFieldComponentPreview() {
    TextFieldComponent("Hi There! 😊", 24.sp)
}

