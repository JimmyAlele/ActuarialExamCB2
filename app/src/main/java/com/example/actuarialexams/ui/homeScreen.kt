package com.example.actuarialexams.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    actuarialExamsViewModel: ActuarialExamsViewModel = viewModel(),
) {
    val actuarialExamsUiState by actuarialExamsViewModel.uiState.collectAsState()

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
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Business Economics",
                            fontSize = 40.sp,
                        )
                        Text(
                            text = "CB2",
                            fontSize = 44.sp
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
            onClick = {navController.navigate("paper selection screen")},
            modifier = Modifier
                .padding(32.dp)) {
            Text(
                text = "Go to questions bank",
                fontSize = 16.sp
            )
        }
    }
}