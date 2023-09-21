package com.example.testapp.data

import com.example.testapp.R

data class DataSource(
    val questionNumber: Int,
    val question: Int,
    val choices: Int,
    val answer: Int,
    val imageRes: Int? = null
)

val questionsList: List<DataSource> = listOf(
    DataSource(R.string.q1, R.string.question_1, R.string.choices_1, 3),
    DataSource(R.string.q2, R.string.question_2, R.string.choices_2, 2),
    DataSource(R.string.q3, R.string.question_3, R.string.choices_3, 1),
    DataSource(R.string.q4, R.string.question_4, R.string.choices_4, 3),
    DataSource(R.string.q5, R.string.question_5, R.string.choices_5, 2),
    DataSource(R.string.q6, R.string.question_6, R.string.choices_6, 1),
    DataSource(R.string.q7, R.string.question_7, R.string.choices_7, 3),
    DataSource(R.string.q8, R.string.question_8, R.string.choices_8, 2),
    DataSource(R.string.q9, R.string.question_9, R.string.choices_9, 2),
    DataSource(R.string.q10, R.string.question_10, R.string.choices_10, 0),
    DataSource(R.string.q11, R.string.question_11, R.string.choices_11, 3),
    DataSource(R.string.q12, R.string.question_12, R.string.choices_12, 2, R.drawable.apr2023_q12_image),
    DataSource(R.string.q13, R.string.question_13, R.string.choices_13, 0),
    DataSource(R.string.q14, R.string.question_14, R.string.choices_14, 1),
    DataSource(R.string.q15, R.string.question_15, R.string.choices_15, 1),
    DataSource(R.string.q16, R.string.question_16, R.string.choices_16, 3),
    DataSource(R.string.q17, R.string.question_17, R.string.choices_17, 3),
    DataSource(R.string.q18, R.string.question_18, R.string.choices_18, 1),
    DataSource(R.string.q19, R.string.question_19, R.string.choices_19, 3),
    DataSource(R.string.q20, R.string.question_20, R.string.choices_20, 0),
    DataSource(R.string.q21, R.string.question_21, R.string.choices_21, 0),
    DataSource(R.string.q22, R.string.question_22, R.string.choices_22, 3),
    DataSource(R.string.q23, R.string.question_23, R.string.choices_23, 0),
    DataSource(R.string.q24, R.string.question_24, R.string.choices_24, 0),
    DataSource(R.string.q25, R.string.question_25, R.string.choices_25, 2),
    DataSource(R.string.q26, R.string.question_26, R.string.choices_26, 1),
    )


data class QuestionsBank (
    val year: Int,
    val session_one: String,
    val session_two: String,
        )

val questionsBankList = listOf(
    QuestionsBank(2023, "April", "September"),
    QuestionsBank(2022, "April", "September"),
    QuestionsBank(2021, "April", "September"),
    QuestionsBank(2020, "April", "September"),
    QuestionsBank(2019, "April", "September"),
    QuestionsBank(2018, "April", "September"),
    QuestionsBank(2017, "April", "September"),
    QuestionsBank(2016, "April", "September"),
    QuestionsBank(2015, "April", "September"),
    QuestionsBank(2014, "April", "September"),
    QuestionsBank(2013, "April", "September"),
    QuestionsBank(2012, "April", "September"),
    QuestionsBank(2011, "April", "September"),
    QuestionsBank(2010, "April", "September"),
    QuestionsBank(2009, "April", "September"),
    QuestionsBank(2008, "April", "September"),
    QuestionsBank(2007, "April", "September"),
    QuestionsBank(2006, "April", "September"),
    QuestionsBank(2005, "April", "September"),
)

