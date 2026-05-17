package kz.technopark.edoox.feature.presentation.navigation

import kotlinx.serialization.Serializable
import kz.technopark.edoox.feature.presentation.error.ErrorStateScreenLauncher
import kz.technopark.edoox.feature.presentation.loading.LoadingLauncher
import kz.technopark.edoox.feature.presentation.model.QuizesDvo
import kz.technopark.edoox.feature.presentation.quiz.QuizLauncher
import kz.technopark.edoox.feature.presentation.review.QuizReviewLauncher
import kz.technopark.edoox.shared.coredata.ErrorType

@Serializable
sealed interface Screen {

    @Serializable
    data object Main : Screen

    @Serializable
    data object SubjectSelection : Screen
    
    @Serializable
    data class Loading(
        val loadingLauncher: LoadingLauncher,
    ) : Screen
    
    @Serializable
    data class Quiz(
        val quizLauncher: QuizLauncher,
    ) : Screen

    @Serializable
    data class QuizReview(
        val quizReviewLauncher: QuizReviewLauncher,
    ): Screen

    @Serializable
    data object Result : Screen

    @Serializable
    data class Error(
        val errorLauncher: ErrorStateScreenLauncher,
    ) : Screen
}