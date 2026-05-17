package kz.technopark.edoox.feature.presentation.quiz

import kotlinx.serialization.Serializable
import kz.technopark.edoox.feature.presentation.model.QuizesDvo

@Serializable
data class QuizLauncher(
    val quizesDvo: QuizesDvo,
)