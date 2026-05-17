package kz.technopark.edoox.feature.presentation.quiz.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.technopark.edoox.feature.presentation.model.QuizDvo
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme

@Composable
internal fun FillQuestionForm(
    dvo: QuizDvo.FillDvo,
    onTextChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = dvo.inputTextFieldValue,
            onValueChange = onTextChanged,
            enabled = !dvo.isAnswered, // Блокируем ввод, если уже проверили
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Введите ответ") },
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )

    }
}

@Preview
@Composable
private fun FillQuestionFormPreview() = EdooxTheme {
    FillQuestionForm(
        dvo = QuizDvo.FillDvo(
            questionTitle = "Question?",
            correctAnswer = "answer1",
            inputTextFieldValue = "",
            isAnswered = false,
            explanation = "Explanation for question #1"
        ),
        onTextChanged = {},
    )
}