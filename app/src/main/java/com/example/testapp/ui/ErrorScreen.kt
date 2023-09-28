package com.example.testapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(
    onHomeButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "This test is not available at the moment",
            fontSize = 20.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Button(
            enabled = true,
            onClick = onHomeButtonClicked
        ) {
            Text(
                text = "Back to Home",
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview () {
    ErrorScreen(onHomeButtonClicked = { /*TODO*/ })
}

