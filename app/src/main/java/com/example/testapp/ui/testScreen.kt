package com.example.testapp.ui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.R
import com.example.testapp.data.DataSource
import java.util.Locale

@Composable
fun TestScreen(
    questions: List<DataSource>,
    enableClickable: Boolean,
    onOptionSelected: (Int, Int, String, MutableList<String>) -> Unit,
    selectedOptions: MutableList<Int?>,
    score: Int,
    onSubmitButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    onReviewTestButtonClicked: () -> Unit,
    testFinished: Boolean,
    reviewTest: Boolean,
    modifier: Modifier
) {
    var answer: Int = 0

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            itemsIndexed (items = questions) { index: Int, item: DataSource ->

                answer = item.answer
                QuestionCard(
                    question = item,
                    enableClickable = enableClickable,
                    onOptionSelected =  onOptionSelected, //{ index: Int, answer: Int, text: String, choices: MutableList<String> -> },
                    selectedOptions = selectedOptions,
                    currentQuestion = index,
                    reviewTest = reviewTest,
                    answer = answer
                )
            }
            item {
                Button(onClick = onSubmitButtonClicked)
                 {
                    Text(
                        text = "Submit",
                        fontSize = 16.sp
                    )
                }
            }

            item {
                Button(onClick = onCancelButtonClicked)
                {
                    Text(
                        text = "Cancel",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
    if (testFinished) {
        FinalScoreDialog(
            score = score,
            onCancelButtonClicked = onCancelButtonClicked,
            onReviewTestButtonClicked = onReviewTestButtonClicked,
            modifier = Modifier
        )
    }
}

@Composable
fun QuestionCard(
    question: DataSource,
    enableClickable: Boolean,
    onOptionSelected: (Int, Int, String, MutableList<String>) -> Unit,
    selectedOptions: MutableList<Int?>,
    currentQuestion: Int,
    reviewTest: Boolean,
    answer: Int,
    modifier: Modifier = Modifier,
) {

    Card(
        elevation = CardDefaults.cardElevation(32.dp),
        shape = RoundedCornerShape(8.dp),
        colors = if (
            reviewTest && selectedOptions[currentQuestion] != question.answer
        ) {
            CardDefaults.cardColors(MaterialTheme.colorScheme.error)
        } else {
            CardDefaults.cardColors()
               },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            //horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = (stringResource(question.questionNumber)),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                //textAlign = TextAlign.Start,

            )
            Text(
                text = stringResource(id = question.question),
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
            RadioGroup(
                questionChoices = question.choices,
                enableClickable = enableClickable,
                onOptionSelected = onOptionSelected,
                selectedOptions = selectedOptions,
                currentQuestion = currentQuestion,
                answer = answer,
            )
        }
    }
}

@Composable
fun RadioGroup(
    questionChoices: Int,
    enableClickable: Boolean,
    onOptionSelected: (Int, Int, String, MutableList<String>) -> Unit,
    selectedOptions: MutableList<Int?>,
    currentQuestion: Int,
    answer: Int,
    modifier: Modifier = Modifier,
) {
    val choices: MutableList<String> = mutableListOf()
    stringResource(id = questionChoices).split("ENDSTOP").forEach{
        choices.add(it.substringAfter(" ")
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
    }

    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
        choices.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    //.height(56.dp)
                    .selectable(
                        selected = (choices.indexOf(text) == selectedOptions[currentQuestion]),
                        enabled = enableClickable,
                        onClick = {onOptionSelected( currentQuestion, answer , text, choices )},
                        role = Role.RadioButton
                    )
                    .padding(start = 4.dp, top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (choices.indexOf(text) == selectedOptions[currentQuestion]),
                    onClick = {onOptionSelected( currentQuestion, answer, text, choices )}
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onCancelButtonClicked: () -> Unit,
    onReviewTestButtonClicked: () -> Unit,
    modifier: Modifier = Modifier

) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
       onDismissRequest =
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
           onReviewTestButtonClicked,
        title = { Text(text = stringResource(R.string.done)) },
        text = { Text(text = stringResource(R.string.you_scored, score)) },
        modifier = modifier,

        confirmButton = {
            TextButton(onClick = onReviewTestButtonClicked) {
                Text(text = stringResource(R.string.review_test))
            }
        },

       dismissButton = {
            TextButton(
                onClick = onCancelButtonClicked
            ) {
                Text(text = stringResource(R.string.home))
            }
        },
    )
}