package com.example.actuarialexams.ui

import com.example.actuarialexams.data.questionsList

/**
 * A data class that represents ActuarialExams UI state
 */
data class ActuarialExamsUiState (
    val questionsAnswered: Int = 0,
    val score: Int = 0,
    val testFinished: Boolean = false
        )