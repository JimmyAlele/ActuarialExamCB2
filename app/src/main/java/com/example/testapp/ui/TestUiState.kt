package com.example.testapp.ui

import com.example.testapp.data.DataSource
import com.example.testapp.data.apr2022questionsList

/**
 * A data class that represents ActuarialExams UI state
 */
data class TestUiState (
    val questionsAnswered: Int = 0,
    val score: Int = 0,
    val testFinished: Boolean = false,
    val reviewTest: Boolean = false,
    val enableClickable: Boolean = true,
    val showAlertDialog: Boolean = true,
    val selectedTest: List<DataSource> = apr2022questionsList,
    var selectedAnswers: MutableList<Int?> = mutableListOf(*arrayOfNulls(NUMBER_OF_QUESTIONS)),
    var scoresList: MutableList<Int> = MutableList(NUMBER_OF_QUESTIONS){0},
    var selectedOptions: MutableList<Int?> = mutableListOf(*arrayOfNulls(NUMBER_OF_QUESTIONS))
)