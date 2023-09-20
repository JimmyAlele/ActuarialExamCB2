package com.example.testapp.ui

/**
 * A data class that represents ActuarialExams UI state
 */
data class TestUiState (
    val questionsAnswered: Int = 0,
    val score: Int = 0,
    val testFinished: Boolean = false,
    val reviewTest: Boolean = false,
    val enableClickable: Boolean = true,
        )