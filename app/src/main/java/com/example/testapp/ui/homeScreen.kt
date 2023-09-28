package com.example.testapp.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onGoToQuestionsBankButtonClicked: () -> Unit,
    onGoToAboutAppScreenButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                    ){
                Spacer(modifier = Modifier.height(120.dp))
                Card(
                    elevation = CardDefaults.cardElevation(32.dp),
                    //colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Business",
                            fontSize = 36.sp,
                        )
                        Text(
                            text = "Economics",
                            fontSize = 36.sp,
                        )
                        Text(
                            text = "CB2",
                            fontSize = 40.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Multiple choices questions bank",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onGoToQuestionsBankButtonClicked,
            modifier = Modifier
                .padding(32.dp)) {
            Text(
                text = "Go to questions bank",
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Button(
            onClick = onGoToAboutAppScreenButtonClicked,
            modifier = Modifier
                .padding(0.dp)) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                Text(
                    text = "About",
                    fontSize = 10.sp
                )
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "information",
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun HomeScreenPreview () {
    HomeScreen(
        onGoToQuestionsBankButtonClicked = { /*TODO*/ },
        onGoToAboutAppScreenButtonClicked = {}
    )
}