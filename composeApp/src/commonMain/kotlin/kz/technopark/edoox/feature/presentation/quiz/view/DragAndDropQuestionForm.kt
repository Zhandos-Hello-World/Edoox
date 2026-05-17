package kz.technopark.edoox.feature.presentation.quiz.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.technopark.edoox.feature.presentation.model.QuizDvo
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme


@Composable
internal fun DragAndDropQuestionForm(
    dvo: QuizDvo.DragAndDropDvo,
    onMatchChanged: (Map<String, String>) -> Unit,
) {
    var selectedLeftItem by remember { mutableStateOf<String?>(null) }
    val currentMatches = remember(dvo.userMatches) { dvo.userMatches.toMutableMap() }

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            dvo.leftColumn.forEach { leftText ->
                val isAlreadyMatched = currentMatches.containsKey(leftText)
                val isSelected = selectedLeftItem == leftText

                Button(
                    onClick = {
                        if (!dvo.isAnswered && !isAlreadyMatched) selectedLeftItem = leftText
                    },
                    enabled = !dvo.isAnswered && !isAlreadyMatched,
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text(text = leftText, maxLines = 1)
                }
            }
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            dvo.rightColumn.forEach { rightText ->
                val isAlreadyMatched = currentMatches.containsValue(rightText)

                Button(
                    onClick = {
                        if (selectedLeftItem != null && !isAlreadyMatched) {
                            currentMatches[selectedLeftItem!!] = rightText
                            onMatchChanged(currentMatches)
                            selectedLeftItem = null // Сбрасываем фокус
                        }
                    },
                    enabled = selectedLeftItem != null && !isAlreadyMatched && !dvo.isAnswered,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text(text = rightText, maxLines = 1)
                }
            }
        }
    }

    if (currentMatches.isNotEmpty()) {
        Spacer(modifier = Modifier.height(24.dp))
        Text("Связанные элементы:", style = MaterialTheme.typography.titleSmall)
        currentMatches.forEach { (k, v) ->
            Text(
                text = "• $k ➔ $v",
                style = MaterialTheme.typography.bodyMedium,
                color = if (dvo.isAnswered) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun DragAndDropQuestionFormPreview() = EdooxTheme {
    DragAndDropQuestionForm(
        dvo = QuizDvo.DragAndDropDvo(
            questionTitle = "Question?",
            leftColumn = listOf("answer1", "answer2", "answer3"),
            rightColumn = listOf("answer4", "answer5", "answer6"),
            map = emptyMap(),
            isAnswered = false,
            explanation = "Explanation for question #1"
        ),
        onMatchChanged = {},
    )
}