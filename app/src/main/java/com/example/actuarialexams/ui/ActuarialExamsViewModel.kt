package com.example.actuarialexams.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.actuarialexams.data.questionsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ActuarialExamsViewModel: ViewModel() {

    // ActuarialExams UI state
    private val _uiState = MutableStateFlow(ActuarialExamsUiState())
    val uiState: StateFlow<ActuarialExamsUiState> = _uiState.asStateFlow()

    private var choices by mutableStateOf(mutableListOf(""))

    private var text by mutableStateOf("")

    private var selectedAnswers: MutableList<Int?> by mutableStateOf(mutableListOf(*arrayOfNulls(questionsList.size)))

    private var scoresList: MutableList<Int> by mutableStateOf(MutableList(questionsList.size){0})

    var selectedOptions: MutableList<Int?> by mutableStateOf(mutableListOf(*arrayOfNulls(questionsList.size)))
    //var selectedOptions: MutableList<Int?> by mutableStateOf(MutableList(questionsList.size){0})
    //var selectedOptions: MutableList<Int?> = MutableList(questionsList.size){2}

    fun getTextChoices(_text: String, _choices: MutableList<String>) {
        text = _text
        choices = _choices
    }

    fun checkAnswer (index: Int, answer: Int) {
        selectedOptions[index] = choices.indexOf(text)
        //selectedAnswers is a mutableList that stores values of the answers entered by the user
        //might be useful
        selectedAnswers[index] = choices.indexOf(text)

        when {
            selectedOptions[index] == answer -> scoresList[index] = 1
            selectedOptions[index] != answer -> scoresList[index] = 0
        }

        _uiState.update { currentState ->
            currentState.copy(
                questionsAnswered = currentState.questionsAnswered.inc()
            )
        }
    }

    fun testFinished () {
        _uiState.update { currentState ->
            currentState.copy(
                score = scoresList.sumOf{it},
                testFinished = true,
            )
        }
    }
}

