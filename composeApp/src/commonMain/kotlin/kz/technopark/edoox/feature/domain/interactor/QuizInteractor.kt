package kz.technopark.edoox.feature.domain.interactor

import kz.technopark.edoox.feature.domain.model.QuizModels
import kz.technopark.edoox.feature.domain.model.QuizParams

interface QuizInteractor {

    suspend fun getQuizQuestions(
        params: List<QuizParams>,
    ): QuizModels

    suspend fun getQuizQuestion(
        params: QuizParams,
    ): QuizModels
}