package kz.technopark.edoox.feature.presentation.model

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kz.technopark.edoox.feature.presentation.loading.LoadedData


@Serializable
data class QuizesDvo(
    val quizTitle: String,
    val questions: List<QuizDvo>,
) : LoadedData

@Serializable
sealed interface QuizDvo {
    val questionTitle: String
    val isAnswered: Boolean
    val explanation: String
    val isCorrect: Boolean

    @Serializable
    @SerialName("choice")
    data class ChoiceDvo(
        override val questionTitle: String,
        override val isAnswered: Boolean = false,
        override val explanation: String,
        val uiAnswers: List<AnswerDvo>,
    ) : QuizDvo {
        override val isCorrect: Boolean get() = uiAnswers.any { it.isSelected && it.isCorrect }

        @Serializable
        data class AnswerDvo(
            val text: String,
            val isSelected: Boolean,
            val isCorrect: Boolean,
            val selectedColor: ULong = Color.Transparent.value,
        )
    }

    @Serializable
    @SerialName("fill")
    data class FillDvo(
        override val questionTitle: String,
        override val isAnswered: Boolean = false,
        override val explanation: String,
        val correctAnswer: String,
        val inputTextFieldValue: String,
    ) : QuizDvo {

        override val isCorrect: Boolean
            get() = inputTextFieldValue.contains(correctAnswer) || correctAnswer.contains(
                inputTextFieldValue
            )
    }


    @Serializable
    @SerialName("drag")
    data class DragAndDropDvo(
        override val questionTitle: String,
        override val isAnswered: Boolean = false,
        override val explanation: String,
        val leftColumn: List<String>,
        val rightColumn: List<String>,
        val map: Map<String, String>,
        val userMatches: Map<String, String> = emptyMap(),
    ) : QuizDvo {

        override val isCorrect: Boolean = userMatches == map
    }
}