package com.example.testapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.data.QuestionsBank
import com.example.testapp.data.questionsBankList

@Composable
fun QuestionsBankScreen(
    onNextButtonClicked: () -> Unit,
    questionsBankList: List<QuestionsBank>
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), 
            contentPadding = PaddingValues(12.dp),
            content = {
                items(questionsBankList.size) {index ->  
                    QuestionsBankCard(
                        questionsBankListItem = questionsBankList[index],
                        onNextButtonClicked = onNextButtonClicked
                    )
                }
            }
        )
    }
}

@Composable
fun QuestionsBankCard(
    questionsBankListItem: QuestionsBank,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                //.fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = questionsBankListItem.year.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = onNextButtonClicked
                )
                    ) {
                Text(
                    text = questionsBankListItem.session_one,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    text = questionsBankListItem.session_two,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun QuestionsBankScreenPreview () {
    QuestionsBankScreen(
        onNextButtonClicked = { },
        questionsBankList
    )
}