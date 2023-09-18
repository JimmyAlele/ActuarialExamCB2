package com.example.actuarialexams.ui

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.actuarialexams.R
import com.example.actuarialexams.data.DataSource
import com.example.actuarialexams.data.questionsList
import java.util.Locale

@Composable
fun PaperSelectionScreen(
    navController: NavHostController,
    actuarialExamsViewModel: ActuarialExamsViewModel = viewModel(),

) {
    val actuarialExamsUiState by actuarialExamsViewModel.uiState.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            itemsIndexed (items = questionsList) { index, item: DataSource ->
                QuestionCard(
                    questionData = item,
                    onOption = {actuarialExamsViewModel.checkAnswer(index = index, answer = item.answer)},
                    selectedOptions = actuarialExamsViewModel.selectedOptions,
                    currentQuestion = index
                )
            }
            item {
                Button(onClick = {
                    actuarialExamsViewModel.testFinished()
                }) {
                    Text(
                        text = "Submit",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
    if (actuarialExamsUiState.testFinished) {
        FinalScoreDialog(
            score = actuarialExamsUiState.score,
            onPlayAgain = { /*actuarialExamsViewModel.resetExam()*/ }
        )
    }
}

@Composable
fun QuestionCard(
    questionData: DataSource,
    onOption: () -> Unit,
    selectedOptions: MutableList<Int?>,
    currentQuestion: Int,
    modifier: Modifier = Modifier,
    actuarialExamsViewModel: ActuarialExamsViewModel = viewModel()
) {
    val actuarialExamsUiState by actuarialExamsViewModel.uiState.collectAsState()
    Card(
        elevation = CardDefaults.cardElevation(32.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.error),
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
                text = (stringResource(questionData.questionNumber)),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                //textAlign = TextAlign.Start,

            )
            Text(
                text = stringResource(id = questionData.question),
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
            RadioGroup(
                questionChoices = questionData.choices,
                onOption = onOption,
                selectedOptions = selectedOptions,
                currentQuestion = currentQuestion
            )
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun RadioGroup(
    questionChoices: Int,
    onOption: () -> Unit,
    selectedOptions: MutableList<Int?>,
    currentQuestion: Int,
    modifier: Modifier = Modifier,
    actuarialExamsViewModel: ActuarialExamsViewModel = viewModel()
) {
    //val actuarialExamsUiState by actuarialExamsViewModel.uiState.collectAsState()
    val choices: MutableList<String> = mutableListOf()
    stringResource(id = questionChoices).split("ENDSTOP").forEach{
        choices.add(it.substringAfter(" ")
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
    }

    //val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
        choices.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    //.height(56.dp)
                    .selectable(
                        selected = (choices.indexOf(text) == selectedOptions[currentQuestion]),
                        onClick = {
                            actuarialExamsViewModel.getTextChoices(text, choices)
                            onOption()
                        },
                        role = Role.RadioButton
                    )
                    .padding(start = 4.dp, top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (choices.indexOf(text) == selectedOptions[currentQuestion]),
                    onClick = {
                        actuarialExamsViewModel.getTextChoices(text, choices)
                        onOption()
                    }
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
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = stringResource(R.string.done)) },
        text = { Text(text = stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(R.string.restart_test))
            }
        }
    )
}