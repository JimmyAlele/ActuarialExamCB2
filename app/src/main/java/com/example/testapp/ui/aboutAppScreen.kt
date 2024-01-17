package com.example.testapp.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.R

@Composable
fun AboutAppScreen(
    onHomeButtonClicked: () -> Unit
) {
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState() ),
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
            text = stringResource(R.string.email),
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.weight(0.2f))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.linkedin.com/in/jimmy-alele")
                try{
                    context.startActivity(intent)
                }catch(e: android.content.ActivityNotFoundException){
                    // open in browser if it fails
                    intent.data = Uri.parse("https://www.linkedin.com/in/jimmy-alele")
                    context.startActivity(intent)
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = "Connect on LinkedIn", fontWeight = FontWeight.Bold)
        }

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