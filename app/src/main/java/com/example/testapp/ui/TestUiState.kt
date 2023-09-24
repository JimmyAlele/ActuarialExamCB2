package com.example.testapp.ui

import com.example.testapp.data.DataSource
import com.example.testapp.data.apr2022questionsList
import com.example.testapp.data.questionsBankList

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
    val selectedTest: List<DataSource> = apr2022questionsList
        )