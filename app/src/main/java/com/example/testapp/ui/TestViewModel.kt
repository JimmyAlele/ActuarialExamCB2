package com.example.testapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.testapp.data.apr2023questionsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Number of questions per test
const val NUMBER_OF_QUESTIONS = 26

class TestViewModel: ViewModel() {

    // ActuarialExams UI state
    private val _uiState = MutableStateFlow(TestUiState())
    val uiState: StateFlow<TestUiState> = _uiState.asStateFlow()

    private var selectedAnswers: MutableList<Int?> by mutableStateOf(mutableListOf(*arrayOfNulls(
        NUMBER_OF_QUESTIONS)))

    private var scoresList: MutableList<Int> by mutableStateOf(MutableList(NUMBER_OF_QUESTIONS){0})

    var selectedOptions: MutableList<Int?> by mutableStateOf(mutableListOf(*arrayOfNulls(
        NUMBER_OF_QUESTIONS)))
    //var selectedOptions: MutableList<Int?> by mutableStateOf(MutableList(questionsList.size){0})
    //var selectedOptions: MutableList<Int?> = MutableList(questionsList.size){2}

    fun checkAnswer (index: Int, answer: List<Int>, text: String, choices: MutableList<String>) {
        selectedOptions[index] = choices.indexOf(text)
        //selectedAnswers is a mutableList that stores values of the answers entered by the user
        //might be useful
        selectedAnswers[index] = choices.indexOf(text)

        when {
            selectedOptions[index] in answer -> scoresList[index] = 1
            selectedOptions[index] !in answer -> scoresList[index] = 0
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
                enableClickable = false,
            )
        }
    }

    fun reviewTest () {
        _uiState.update { currentState ->
            currentState.copy(
                reviewTest = true,
                showAlertDialog = false,
                enableClickable = false
            )
        }
    }

    fun resetTest () {
        _uiState.update { currentState ->
            currentState.copy(
            testFinished = false,
            reviewTest = false,
            enableClickable = true,
            showAlertDialog = true
            )
        }
        //selectedAnswers.clear()
        //selectedOptions.clear()
        //scoresList.clear()
    }
}

