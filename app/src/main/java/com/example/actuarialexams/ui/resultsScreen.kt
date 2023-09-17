package com.example.actuarialexams.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ResultsScreen(navController: NavHostController) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Screen C",
            fontSize = 64.sp,
        )
        Spacer(modifier = Modifier.height(65.dp))
        Button(onClick = {
            navController.navigate("A") {
                // below ensures that a single back click when on screen A closes the app
                popUpTo("A"){inclusive = true}
            }
        }) {
            Text(
                text = "Go to Screen A",
                fontSize = 45.sp
            )
        }
        Spacer(modifier = Modifier.height(65.dp))
        Button(onClick = {
            navController.navigate("B")
        }) {
            Text(
                text = "Go to Screen B",
                fontSize = 45.sp
            )
        }
    }
}