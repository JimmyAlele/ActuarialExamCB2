package com.example.testapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testapp.data.apr2012questionsList
import com.example.testapp.data.apr2022questionsList
import com.example.testapp.data.questionsBankList
import com.example.testapp.data.apr2023questionsList

enum class TestAppScreens() {
    HomeScreen,
    QuestionsBankScreen,
    TestScreen,
    TestReviewScreen
}

/**
 * This is the navigation graph file.
 * This file outlines the routes.
 * On creation of the app, the TestApp() functions is called (see MainActivity file).
 * The TestApp() functions sets the start screen as route HomeScreen which is HomeScreen
 */
@Composable
fun TestApp(
    navController: NavHostController = rememberNavController(),
    viewModel: TestViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = TestAppScreens.HomeScreen.name,
        modifier = Modifier,
    ) {
        composable(route = TestAppScreens.HomeScreen.name){
            HomeScreen(
                onGoToQuestionsBankButtonClicked = {
                    navController.navigate(TestAppScreens.QuestionsBankScreen.name)
                }
            )
        }

        composable(route = TestAppScreens.QuestionsBankScreen.name){
            QuestionsBankScreen(
                questionsBankList = questionsBankList,
                onNextButtonClicked = {
                    navController.navigate(TestAppScreens.TestScreen.name)
                }
            )
        }

        composable(route = TestAppScreens.TestScreen.name){
            TestScreen(
                questions = apr2022questionsList,
                enableClickable = uiState.enableClickable,
                onOptionSelected = { index: Int, answer: List<Int>, text: String, choices: MutableList<String> ->
                    viewModel.checkAnswer(index, answer, text, choices) },
                selectedOptions = viewModel.selectedOptions,
                score = uiState.score,
                onSubmitButtonClicked = { viewModel.testFinished() },
                onHomeButtonClicked = {
                    viewModel.resetTest()
                    navController.popBackStack(TestAppScreens.QuestionsBankScreen.name, inclusive = false)
                                        },
                onReviewTestButtonClicked = {
                    viewModel.reviewTest()
                    navController.navigate(TestAppScreens.TestReviewScreen.name)
                                            },
                testFinished = uiState.testFinished,
                reviewTest = uiState.reviewTest,
                showAlertDialog = uiState.showAlertDialog,
                modifier = Modifier
            )
        }

        composable(route = TestAppScreens.TestReviewScreen.name){
            TestScreen(
                questions = apr2022questionsList,
                enableClickable = uiState.enableClickable,
                onOptionSelected = { index: Int, answer: List<Int>, text: String, choices: MutableList<String> ->
                    viewModel.checkAnswer(index, answer, text, choices) },
                selectedOptions = viewModel.selectedOptions,
                score = uiState.score,
                onSubmitButtonClicked = { viewModel.testFinished() },
                onHomeButtonClicked = {
                    viewModel.resetTest()
                    navController.popBackStack(TestAppScreens.QuestionsBankScreen.name, inclusive = false)
                                        },
                onReviewTestButtonClicked = {
                    viewModel.reviewTest()
                    navController.navigate(TestAppScreens.TestReviewScreen.name)
                },
                testFinished = uiState.testFinished,
                reviewTest = uiState.reviewTest,
                showAlertDialog = uiState.showAlertDialog,
                modifier = Modifier
            )
        }
    }
}