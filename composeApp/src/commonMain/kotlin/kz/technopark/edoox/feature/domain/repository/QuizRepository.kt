package kz.technopark.edoox.feature.domain.repository

import kz.technopark.edoox.feature.domain.model.QuizModel
import kz.technopark.edoox.feature.domain.model.QuizParams

interface QuizRepository {

    suspend fun generateChoiceQuestion(
        quizParams: QuizParams
    ) : List<QuizModel.Choice>

    suspend fun generateFillQuestion(
        quizParams: QuizParams
    ) : List<QuizModel.Fill>

    suspend fun generateDragAndDrop(
        quizParams: QuizParams
    ) : List<QuizModel.DragAndDrop>
}