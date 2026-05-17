package kz.technopark.edoox.feature.data.mapper

import kz.technopark.edoox.feature.data.model.QuizChoiceResponseDto
import kz.technopark.edoox.feature.domain.model.QuizModel

class QuizChoiceMapper {

    fun toQuizChoiceModel(from: QuizChoiceResponseDto): List<QuizModel.Choice> {
        return from.questions.map {
            QuizModel.Choice(
                questionText = it.question,
                explanation = it.explanation,
                answers = it.answers.map { answer ->
                    QuizModel.Choice.Answer(
                        text = answer.answer,
                        isCorrect = answer.correct,
                    )
                }
            )
        }
    }
}