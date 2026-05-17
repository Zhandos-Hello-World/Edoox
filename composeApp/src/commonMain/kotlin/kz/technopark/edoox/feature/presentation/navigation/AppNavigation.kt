package kz.technopark.edoox.feature.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kz.technopark.edoox.feature.presentation.error.ErrorScreen
import kz.technopark.edoox.feature.presentation.error.ErrorScreenEntryPoint
import kz.technopark.edoox.feature.presentation.loading.LoadingScreenEntryPoint
import kz.technopark.edoox.feature.presentation.main.MainScreenEntryPoint
import kz.technopark.edoox.feature.presentation.quiz.QuizScreenEntryPoint
import kz.technopark.edoox.feature.presentation.review.QuizReviewScreenEntryPoint
import kz.technopark.edoox.feature.presentation.subject.SubjectSelectionScreenEntryPoint

@Composable
fun AppNavigation(
    router: AppRouter,
) {
    val stack by router.navigationStack.collectAsState()

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = stack,
        onBack = { router.pop() },
        entryProvider = { key ->
            when (key) {
                Screen.Main -> NavEntry(key) {
                    IsolateScreenScope {
                        MainScreenEntryPoint()
                    }
                }

                Screen.SubjectSelection -> NavEntry(key) {
                    IsolateScreenScope {
                        SubjectSelectionScreenEntryPoint()
                    }
                }

                is Screen.Error -> NavEntry(key) {
                    IsolateScreenScope {
                        ErrorScreenEntryPoint(
                            launcher = key.errorLauncher
                        )
                    }
                }

                is Screen.Loading -> NavEntry(key) {
                    IsolateScreenScope {
                        LoadingScreenEntryPoint(key.loadingLauncher)
                    }
                }

                is Screen.Quiz -> NavEntry(key) {
                    IsolateScreenScope {
                        QuizScreenEntryPoint(
                            launcher = key.quizLauncher,
                        )
                    }
                }

                Screen.Result -> NavEntry(key) {
                    IsolateScreenScope {
                        SubjectSelectionScreenEntryPoint()
                    }
                }

                is Screen.QuizReview -> NavEntry(key) {
                    IsolateScreenScope {
                        QuizReviewScreenEntryPoint(
                            launcher = key.quizReviewLauncher,
                        )
                    }
                }
            }
        },
    )
}