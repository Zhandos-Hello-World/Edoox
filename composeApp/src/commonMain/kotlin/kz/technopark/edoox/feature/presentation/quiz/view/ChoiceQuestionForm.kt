package kz.technopark.edoox.feature.presentation.quiz.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.technopark.edoox.feature.presentation.model.QuizDvo
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme

@Composable
internal fun ChoiceQuestionForm(
    dvo: QuizDvo.ChoiceDvo,
    onAnswerClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        dvo.uiAnswers.forEachIndexed { index, answer ->
            val parsedColor = remember(answer.selectedColor) {
                Color(answer.selectedColor)
            }

            val isSelectedNotAnswered = answer.isSelected && !dvo.isAnswered
            val backgroundColor = when {
                isSelectedNotAnswered -> MaterialTheme.colorScheme.primaryContainer
                dvo.isAnswered && answer.isSelected -> parsedColor.copy(alpha = 0.2f)
                else -> Color.Transparent
            }
            val strokeColor = when {
                isSelectedNotAnswered -> MaterialTheme.colorScheme.primary
                dvo.isAnswered -> parsedColor
                else -> MaterialTheme.colorScheme.outline
            }

            OutlinedButton(
                onClick = { onAnswerClick(index) },
                modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 56.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.outlinedButtonColors(containerColor = backgroundColor),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(strokeColor),
                    width = if (answer.isSelected || dvo.isAnswered) 2.dp else 1.dp
                )
            ) {
                Text(
                    text = answer.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (isSelectedNotAnswered) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


@Preview
@Composable
private fun ChoiceQuestionFormPreview() = EdooxTheme {
    ChoiceQuestionForm(
        dvo = QuizDvo.ChoiceDvo(
            questionTitle = "Question?",
            uiAnswers = listOf(
                QuizDvo.ChoiceDvo.AnswerDvo(
                    text = "answer DKWLMA DLKMAW LKMA DKLWAD MKLAW MDKLAW MDLA DMAL MD LAKWMD LKWA DM LKAW DMKWL MDLKM ADL MAWDM WAKLM DAKLWM DAW1",
                    isSelected = true,
                    isCorrect = false,
                    selectedColor = Color.Red.value,
                ),
                QuizDvo.ChoiceDvo.AnswerDvo(
                    text = "answer2",
                    isSelected = true,
                    isCorrect = false,
                    selectedColor = Color.Green.value,
                ),
                QuizDvo.ChoiceDvo.AnswerDvo(
                    text = "answer3",
                    isSelected = false,
                    isCorrect = false,
                ),
            ),
            isAnswered = false,
            explanation = "Explanation for question #1"
        ),
        onAnswerClick = {}
    )
}