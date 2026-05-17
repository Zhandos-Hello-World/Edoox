package kz.technopark.edoox.feature.presentation.quiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.technopark.edoox.feature.presentation.model.QuizDvo
import kz.technopark.edoox.feature.presentation.quiz.view.ChoiceQuestionForm
import kz.technopark.edoox.feature.presentation.quiz.view.DragAndDropQuestionForm
import kz.technopark.edoox.feature.presentation.quiz.view.FillQuestionForm
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme
import kz.technopark.edoox.shared.coreui.theme.language.AppStrings
import kz.technopark.edoox.shared.coreui.view.PrimaryButton
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun QuizScreenEntryPoint(launcher: QuizLauncher) {
    val viewModel = koinViewModel<QuizViewModel> { parametersOf(launcher) }
    val uiState by viewModel.uiState.collectAsState()

    QuizScreen(
        state = uiState,
        onActionClick = { viewModel.handleActionClick() },
        onChoiceSelected = { index -> viewModel.selectChoiceAnswer(index) },
        onFillChanged = { text -> viewModel.onFillTextChanged(text) },
        onDragDropMatched = { map -> viewModel.onDragAndDropMatch(map) })
}


@Composable
fun QuizScreen(
    state: QuizUiState,
    onActionClick: () -> Unit,
    onChoiceSelected: (Int) -> Unit,
    onFillChanged: (String) -> Unit,
    onDragDropMatched: (Map<String, String>) -> Unit,
) {
    Scaffold(
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                modifier = Modifier.fillMaxWidth(),
            ) {
                val currentQuestion = state.currentQuestion

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .padding(horizontal = 16.dp),
                    onClick = onActionClick,
                    text = if (currentQuestion?.isAnswered == true) AppStrings.current.next else AppStrings.current.check,
                    enabled = state.isSubmitButtonEnabled,

                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        content = { paddingValues ->
            QuizContent(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                state = state,
                onChoiceSelected = onChoiceSelected,
                onFillChanged = onFillChanged,
                onDragDropMatched = onDragDropMatched
            )
        },
    )
}

@Composable
fun QuizContent(
    modifier: Modifier,
    state: QuizUiState,
    onChoiceSelected: (Int) -> Unit,
    onFillChanged: (String) -> Unit,
    onDragDropMatched: (Map<String, String>) -> Unit,
) = Column(modifier) {
    val totalQuestions = state.questions.size
    val progressTarget = if (totalQuestions > 0) {
        (state.currentQuestionIndex + 1).toFloat() / totalQuestions
    } else 0f

    val animatedProgress by animateFloatAsState(
        targetValue = progressTarget,
        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
    )

    LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp).padding(horizontal = 16.dp)
            .height(8.dp),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    )

    AnimatedContent(
        targetState = state.currentQuestionIndex, transitionSpec = {
            (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it } + fadeOut())
        }, modifier = Modifier.fillMaxSize()
    ) { targetIndex ->
        val question = state.questions.getOrNull(targetIndex)

        if (question != null) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = question.questionTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                when (question) {
                    is QuizDvo.ChoiceDvo -> ChoiceQuestionForm(
                        dvo = question,
                        onAnswerClick = onChoiceSelected,
                    )

                    is QuizDvo.FillDvo -> FillQuestionForm(
                        dvo = question,
                        onTextChanged = onFillChanged,
                    )

                    is QuizDvo.DragAndDropDvo -> DragAndDropQuestionForm(
                        dvo = question,
                        onMatchChanged = onDragDropMatched,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QuizScreenPreview() = EdooxTheme(darkTheme = !true) {
    QuizScreen(
        state = QuizUiState(
            title = "Title",
            questions = listOf(
                QuizDvo.ChoiceDvo(
                    questionTitle = "Question?",
                    uiAnswers = listOf(
                        QuizDvo.ChoiceDvo.AnswerDvo(
                            text = "answer1",
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
            ),
            currentQuestionIndex = 0,
            isQuizFinished = false,
            score = 0,
            isSubmitButtonEnabled = false,
        ),
        onActionClick = {},
        onChoiceSelected = {},
        onFillChanged = {},
        onDragDropMatched = {},
    )
}