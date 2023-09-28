package com.example.testapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.R

@Composable
fun AboutAppScreen(
    onHomeButtonClicked: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "About this app",
            fontSize = 30.sp,
            modifier = Modifier
                .padding(8.dp)
        )
        Text (
            text = stringResource(id = R.string.about_the_app),
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                )

        Spacer(modifier = Modifier.weight(0.2f))

        Text (
            text = stringResource(id = R.string.feedback),
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.weight(0.2f))

        Text (
            text = "I hope you find this app useful.",
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onHomeButtonClicked       
        ) {
            Text(
                text = "Back",
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutAppScreenPreview () {
    AboutAppScreen(onHomeButtonClicked = { /*TODO*/ })
}