package com.example.actuarialexams.data

import com.example.actuarialexams.R

data class DataSource(
    val questionNumber: Int,
    val question: Int,
    val choices: Int,
    val answer: Int
)

val questionsList: List<DataSource> = listOf(
    DataSource(R.string.q1, R.string.question_1, R.string.choices_1, R.string.answer_1),
    DataSource(R.string.q2, R.string.question_2, R.string.choices_2, R.string.answer_2),
    DataSource(R.string.q3, R.string.question_3, R.string.choices_3, R.string.answer_3),
    DataSource(R.string.q4, R.string.question_4, R.string.choices_4, R.string.answer_4),
    DataSource(R.string.q5, R.string.question_5, R.string.choices_5, R.string.answer_5),
    DataSource(R.string.q6, R.string.question_6, R.string.choices_6, R.string.answer_6),
    DataSource(R.string.q7, R.string.question_7, R.string.choices_7, R.string.answer_7),
    DataSource(R.string.q8, R.string.question_8, R.string.choices_8, R.string.answer_8),
    DataSource(R.string.q9, R.string.question_9, R.string.choices_9, R.string.answer_9),
    DataSource(R.string.q10, R.string.question_10, R.string.choices_10, R.string.answer_10),
    DataSource(R.string.q11, R.string.question_11, R.string.choices_11, R.string.answer_11),
    DataSource(R.string.q12, R.string.question_12, R.string.choices_12, R.string.answer_12),
    DataSource(R.string.q13, R.string.question_13, R.string.choices_13, R.string.answer_13),
    DataSource(R.string.q14, R.string.question_14, R.string.choices_14, R.string.answer_14),
    DataSource(R.string.q15, R.string.question_15, R.string.choices_15, R.string.answer_15),
    DataSource(R.string.q16, R.string.question_16, R.string.choices_16, R.string.answer_16),
    DataSource(R.string.q17, R.string.question_17, R.string.choices_17, R.string.answer_17),
    DataSource(R.string.q18, R.string.question_18, R.string.choices_18, R.string.answer_18),
    DataSource(R.string.q19, R.string.question_19, R.string.choices_19, R.string.answer_19),
    DataSource(R.string.q20, R.string.question_20, R.string.choices_20, R.string.answer_20),
    DataSource(R.string.q21, R.string.question_21, R.string.choices_21, R.string.answer_21),
    DataSource(R.string.q22, R.string.question_22, R.string.choices_22, R.string.answer_22),
    DataSource(R.string.q23, R.string.question_23, R.string.choices_23, R.string.answer_23),
    DataSource(R.string.q24, R.string.question_24, R.string.choices_24, R.string.answer_24),
    DataSource(R.string.q25, R.string.question_25, R.string.choices_25, R.string.answer_25),
    DataSource(R.string.q26, R.string.question_26, R.string.choices_26, R.string.answer_26)
)
