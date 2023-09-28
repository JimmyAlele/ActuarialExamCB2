package com.example.testapp.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testapp.data.DataSource
import com.example.testapp.data.questionsBankList

enum class TestAppScreens() {
    HomeScreen,
    QuestionsBankScreen,
    TestScreen,
    TestReviewScreen,
    AboutAppScreen,
    ErrorScreen
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
        composable(
            route = TestAppScreens.AboutAppScreen.name,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { fullWidth -> fullWidth }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(500),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
        ) {
            AboutAppScreen(
                onHomeButtonClicked = {
                    navController.popBackStack(TestAppScreens.HomeScreen.name, inclusive = false)
                }
            )
        }

        composable(
            route = TestAppScreens.ErrorScreen.name,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { fullWidth -> fullWidth }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(500),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
        ) {
            ErrorScreen(
                onHomeButtonClicked = {
                    navController.popBackStack(TestAppScreens.QuestionsBankScreen.name, inclusive = false)
                }
            )
        }

        composable(
            route = TestAppScreens.HomeScreen.name,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(500,)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(500),
                    targetOffsetX = { fullWidth -> -fullWidth }
                )
            }
        ){
            HomeScreen(
                onGoToQuestionsBankButtonClicked = {
                    navController.navigate(TestAppScreens.QuestionsBankScreen.name)
                },
                onGoToAboutAppScreenButtonClicked = {
                    navController.navigate(TestAppScreens.AboutAppScreen.name)
                }
            )
        }

        composable(
            route = TestAppScreens.QuestionsBankScreen.name,
            enterTransition = {
                              slideInHorizontally(
                                  animationSpec = tween(500),
                                  initialOffsetX = { fullWidth -> fullWidth }
                              )
            },
            popEnterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { fullWidth -> -fullWidth }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(500),
                    targetOffsetX = { fullWidth -> -fullWidth }
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(500),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
        ){
            QuestionsBankScreen(
                questionsBankList = questionsBankList,
                onNextButtonClicked = { test: List<DataSource> ->
                    if (test.isNotEmpty()) {
                        viewModel.selectTest(test)
                        navController.navigate(TestAppScreens.TestScreen.name)
                    } else {
                        navController.navigate(TestAppScreens.ErrorScreen.name)
                    }

                }
            )
        }

        composable(
            route = TestAppScreens.TestScreen.name,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { fullWidth -> fullWidth })
            },
            popEnterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { fullWidth -> -fullWidth })
            },
            popExitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(500),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(10),
                )
            }

        ){
            TestScreen(
                questions = uiState.selectedTest,
                enableClickable = uiState.enableClickable,
                onOptionSelected = { index: Int, answer: List<Int>, text: String, choices: MutableList<String> ->
                    viewModel.checkAnswer(index, answer, text, choices) },
                selectedOptions = uiState.selectedOptions,
                score = uiState.score,
                onSubmitButtonClicked = { viewModel.testFinished() },
                onHomeButtonClicked = {
                    viewModel.resetTest()
                    navController.popBackStack(TestAppScreens.QuestionsBankScreen.name, inclusive = false)
                                        },
                onReviewTestButtonClicked = {
                    viewModel.reviewTest()
                    // remove TestScreen from backstack
                    navController.navigate(TestAppScreens.TestReviewScreen.name){popUpTo(TestAppScreens.QuestionsBankScreen.name)}
                                            },
                testFinished = uiState.testFinished,
                reviewTest = uiState.reviewTest,
                showAlertDialog = uiState.showAlertDialog,
                modifier = Modifier
            )
        }

        composable(
            route = TestAppScreens.TestReviewScreen.name,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { fullWidth -> fullWidth })
            },
            popExitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(500),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(500),
                    targetOffsetX = { fullWidth -> fullWidth }
                )
            }
                ){
            TestScreen(
                questions = uiState.selectedTest,
                enableClickable = uiState.enableClickable,
                onOptionSelected = { index: Int, answer: List<Int>, text: String, choices: MutableList<String> ->
                    viewModel.checkAnswer(index, answer, text, choices) },
                selectedOptions = uiState.selectedOptions,
                score = uiState.score,
                onSubmitButtonClicked = { viewModel.testFinished() },
                onHomeButtonClicked = {
                    navController.popBackStack(TestAppScreens.QuestionsBankScreen.name, inclusive = false)
                    viewModel.resetTest()
                                        },
                onReviewTestButtonClicked = {
                    navController.navigate(TestAppScreens.TestReviewScreen.name)
                    viewModel.reviewTest()
                },
                testFinished = uiState.testFinished,
                reviewTest = uiState.reviewTest,
                showAlertDialog = uiState.showAlertDialog,
                modifier = Modifier
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TestAppPreview () {
    TestApp()
}