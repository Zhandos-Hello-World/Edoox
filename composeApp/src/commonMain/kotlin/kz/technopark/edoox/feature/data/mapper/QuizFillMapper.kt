package kz.technopark.edoox.feature.data.mapper

import kz.technopark.edoox.feature.data.model.QuizFillAnswerResponseDto
import kz.technopark.edoox.feature.domain.model.QuizModel

class QuizFillMapper {

    fun toQuizFillModel(from: QuizFillAnswerResponseDto): List<QuizModel.Fill> {
        return from.questions.map {
            QuizModel.Fill(
                questionText = it.question,
                correctAnswer = it.correctAnswer,
                explanation = it.explanation,
            )
        }
    }
}