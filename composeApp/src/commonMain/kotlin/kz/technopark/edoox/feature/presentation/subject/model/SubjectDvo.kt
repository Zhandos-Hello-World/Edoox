package kz.technopark.edoox.feature.presentation.subject.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kz.technopark.edoox.feature.domain.model.QuizParams

data class SubjectDvo(
    val subjectType: QuizParams.Subject,
    val id: String,
    val name: String,
    val color: Color,
    val icon: ImageVector,
    val isSelected: Boolean,
)