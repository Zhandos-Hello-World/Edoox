package kz.technopark.edoox.feature.presentation.mapper

import kz.technopark.edoox.feature.domain.model.QuizModel
import kz.technopark.edoox.feature.domain.model.QuizModels
import kz.technopark.edoox.feature.presentation.model.QuizDvo
import kz.technopark.edoox.feature.presentation.model.QuizesDvo

class QuizDvoMapper {

    fun toQuizesDvo(from: QuizModels): QuizesDvo {
        return QuizesDvo(
            quizTitle = "Title", questions = from.quizModels.map { toQuizModelDvo(it) })
    }

    private fun toQuizModelDvo(from: QuizModel): QuizDvo {
        return when (from) {
            is QuizModel.Choice -> QuizDvo.ChoiceDvo(
                questionTitle = from.questionText,
                uiAnswers = from.answers.map {
                    QuizDvo.ChoiceDvo.AnswerDvo(
                        text = it.text,
                        isSelected = false,
                        isCorrect = it.isCorrect,
                    )
                },
                explanation = from.explanation,
            )

            is QuizModel.Fill -> QuizDvo.FillDvo(
                questionTitle = from.questionText,
                correctAnswer = from.correctAnswer,
                explanation = from.explanation,
                inputTextFieldValue = "",
            )

            is QuizModel.DragAndDrop -> QuizDvo.DragAndDropDvo(
                questionTitle = "Сопоставьте элементы",
                leftColumn = from.connectedText.keys.toList(),
                rightColumn = from.connectedText.values.toList().shuffled(),
                explanation = from.explanation,
                map = from.connectedText,
            )
        }
    }
}