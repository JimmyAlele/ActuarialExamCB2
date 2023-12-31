package com.example.testapp.ui

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    onOptionSelected: (Int, List<Int>, String, MutableList<String>) -> Unit,
    selectedOptions: MutableList<Int?>,
    score: Int,
    onSubmitButtonClicked: () -> Unit,
    onHomeButtonClicked: () -> Unit,
    onReviewTestButtonClicked: () -> Unit,
    testFinished: Boolean,
    reviewTest: Boolean,
    showAlertDialog: Boolean,
    modifier: Modifier
) {
    var answer: List<Int> = listOf()

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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(onClick = onHomeButtonClicked)
                    {
                        Text(
                            text = "Back to Home",
                            fontSize = 16.sp
                        )
                    }

                    Button(
                        enabled = enableClickable,
                        onClick = onSubmitButtonClicked
                    )
                    {
                        Text(
                            text = "Submit",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
    // this animation has no effect on the created AlertDialog
    AnimatedVisibility (
        visible = testFinished && showAlertDialog,
        enter = slideInHorizontally(),
        exit = scaleOut(
            animationSpec = tween(500)
            //tween(2000, easing = CubicBezierEasing(0.08f,0.93f,0.68f,1.27f)
            ),
    ) {
        FinalScoreDialog(
            score = score,
            onHomeButtonClicked = onHomeButtonClicked,
            onReviewTestButtonClicked = onReviewTestButtonClicked,
            modifier = Modifier
        )
    }
}

@Composable
fun QuestionCard(
    question: DataSource,
    enableClickable: Boolean,
    onOptionSelected: (Int, List<Int>, String, MutableList<String>) -> Unit,
    selectedOptions: MutableList<Int?>,
    currentQuestion: Int,
    reviewTest: Boolean,
    answer: List<Int>,
    modifier: Modifier = Modifier,
) {

    Card(
        elevation = CardDefaults.cardElevation(32.dp),
        shape = RoundedCornerShape(8.dp),
        colors = if (
            reviewTest && selectedOptions[currentQuestion] !in question.answer
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
            if (!reviewTest) {
                Text(
                    text = (stringResource(question.questionNumber)),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    //textAlign = TextAlign.Start,
                )
            } else {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        ) {
                    Text(
                        text = (stringResource(question.questionNumber)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    // displays the correct answer options on the top right of the card
                    /*Text(
                        text = ("Correct Answer: Option ${
                            answer.map{ it + 1}
                        }"),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )*/
                }
            }

            Text(
                text = stringResource(id = question.question),
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )

            if (question.imageRes != null) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(120.dp)
                        ) {
                    Image(
                        painter = painterResource(question.imageRes),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(500.dp)
                    )
                }
            }

            RadioGroup(
                questionChoices = question.choices,
                enableClickable = enableClickable,
                onOptionSelected = onOptionSelected,
                selectedOptions = selectedOptions,
                currentQuestion = currentQuestion,
                reviewTest = reviewTest,
                answer = answer,
            )
        }
    }
}

@Composable
fun RadioGroup(
    questionChoices: Int,
    enableClickable: Boolean,
    onOptionSelected: (Int, List<Int>, String, MutableList<String>) -> Unit,
    selectedOptions: MutableList<Int?>,
    currentQuestion: Int,
    answer: List<Int>,
    reviewTest: Boolean,
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
                    .selectable(
                        selected = (choices.indexOf(text) == selectedOptions[currentQuestion]),
                        enabled = enableClickable,
                        onClick = { onOptionSelected(currentQuestion, answer, text, choices) },
                        role = Role.RadioButton
                    )
                    .padding(start = 4.dp, top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (reviewTest) {
                    // show icons that highlight whether the option is correct or wrong
                    // will only show when the app is in reviewTest mode
                    when {
                        choices.indexOf(text) in answer
                                && choices.indexOf(text) == selectedOptions[currentQuestion] -> Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "tick"
                        )

                        choices.indexOf(text) == selectedOptions[currentQuestion]
                                && choices.indexOf(text) !in answer -> Icon(
                            painter = painterResource(id = R.drawable.cancel_24px),
                            contentDescription = "cross")

                        choices.indexOf(text) in answer -> Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "tick"
                        )

                        else -> {Icon(
                            painterResource(id = R.drawable.radio_button_unchecked_24px),
                            contentDescription = null
                        )}
                    }
                } else {
                    //show ordinary radio buttons if test is in progress
                    RadioButton(
                        enabled = enableClickable,
                        selected = (choices.indexOf(text) == selectedOptions[currentQuestion]),
                        onClick = {onOptionSelected( currentQuestion, answer, text, choices )}
                    )
                }
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
    onHomeButtonClicked: () -> Unit,
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
                onClick = onHomeButtonClicked
            ) {
                Text(text = stringResource(R.string.home))
            }
        },
    )
}