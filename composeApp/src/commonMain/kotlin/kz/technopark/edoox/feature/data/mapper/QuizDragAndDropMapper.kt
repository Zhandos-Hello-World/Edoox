package kz.technopark.edoox.feature.data.mapper

import kz.technopark.edoox.feature.data.model.QuizDragAndDropResponseDto
import kz.technopark.edoox.feature.domain.model.QuizModel

class QuizDragAndDropMapper {

    fun toQuizDragAndDropModel(from: QuizDragAndDropResponseDto): List<QuizModel.DragAndDrop> {
        return from.quizDragAndDrops.map {
            QuizModel.DragAndDrop(
                connectedText = it.connectedText,
                explanation = it.explanation,
            )
        }
    }
}