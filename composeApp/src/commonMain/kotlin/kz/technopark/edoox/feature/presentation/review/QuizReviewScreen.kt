package kz.technopark.edoox.feature.presentation.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edoox.composeapp.generated.resources.Res
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import kz.technopark.edoox.feature.presentation.model.QuizDvo
import kz.technopark.edoox.feature.presentation.model.QuizesDvo
import kz.technopark.edoox.feature.presentation.review.view.SwipeableCard
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme
import kz.technopark.edoox.shared.coreui.theme.language.AppStrings
import kz.technopark.edoox.shared.coreui.view.NavBar
import kz.technopark.edoox.shared.coreui.view.PrimaryButton
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun QuizReviewScreenEntryPoint(
    launcher: QuizReviewLauncher,
) {
    val appTranslations = AppStrings.current
    val viewModel: QuizReviewViewModel = koinViewModel {
        parametersOf(
            appTranslations,
            launcher,
        )
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    QuizReviewScreen(
        state = uiState,
        controller = viewModel,
    )
}

@Composable
fun QuizReviewScreen(
    state: QuizReviewUIState,
    controller: QuizReviewController,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            NavBar(
                title = state.title,
                showBackButton = true,
                onBackClick = { controller.onBackClicked() },
            )
        },
        content = { paddingValues ->
            QuizReviewContent(
                modifier = Modifier.padding(paddingValues),
                cardStack = state.quiz,
                onCardSwiped = { swipedRight ->
                    controller.onCardDismissed(swipedRight)
                })
        },
        bottomBar = {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = controller::onNextClicked,
                text = state.onPrimaryButtonText,
            )
        },
    )
}

@Composable
fun QuizReviewContent(
    modifier: Modifier = Modifier,
    cardStack: List<QuizDvo>,
    onCardSwiped: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp), contentAlignment = Alignment.Center
    ) {
        if (cardStack.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition {
                    val jsonString = Res.readBytes("files/rocket.json").decodeToString()
                    LottieCompositionSpec.JsonString(jsonString)
                }
                val progress by animateLottieCompositionAsState(
                    composition = composition, iterations = Compottie.IterateForever
                )

                Image(
                    painter = rememberLottiePainter(composition, progress = { progress }),
                    contentDescription = "Success Animation",
                    modifier = Modifier.size(200.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = AppStrings.current.allCaughtUp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            cardStack.asReversed().forEachIndexed { index, item ->
                val isTopCard = index == cardStack.lastIndex

                if (isTopCard || index >= cardStack.lastIndex - 2) {
                    SwipeableCard(
                        modifier = Modifier,
                        onSwipedLeft = { onCardSwiped(false) },
                        onSwipedRight = { onCardSwiped(true) },
                        content = { ReviewCardContent(item) },
                    )
                }
            }
        }
    }
}

@Composable
fun ReviewCardContent(item: QuizDvo) {
    Card(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95f),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp)
        ) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(50))
                    .background(if (item.isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = if (item.isCorrect) "CORRECT" else "INCORRECT",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = item.questionTitle,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(24.dp))
            Divider(color = Color(0xFF333333), thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))

            val userAnswer = when (item) {
                is QuizDvo.ChoiceDvo -> item.uiAnswers.first { it.isSelected }.text
                is QuizDvo.DragAndDropDvo -> {
                    item.userMatches.entries.joinToString(separator = "\n") { (question, answer) ->
                        "$question ➔ ${answer ?: "-"}"
                    }
                }

                is QuizDvo.FillDvo -> item.inputTextFieldValue
            }

            val correctAnswer = when (item) {
                is QuizDvo.ChoiceDvo -> item.uiAnswers.first { it.isCorrect }.text
                is QuizDvo.DragAndDropDvo -> {
                    item.map.entries.joinToString(separator = "\n") { (question, answer) ->
                        "$question ➔ ${answer ?: "-"}"
                    }
                }

                is QuizDvo.FillDvo -> item.correctAnswer
            }



            AnswerRow(
                title = AppStrings.current.yourSelection,
                answerText = userAnswer,
                isCorrect = item.isCorrect,
            )

            if (!item.isCorrect) {
                Spacer(modifier = Modifier.height(16.dp))
                AnswerRow(
                    title = AppStrings.current.correctAnswer,
                    answerText = correctAnswer,
                    isCorrect = true,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier.fillMaxWidth().weight(1f).clip(RoundedCornerShape(16.dp))
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                    ).padding(16.dp)
            ) {
                Column {
                    Text(
                        text = AppStrings.current.explanation,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.explanation,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = AppStrings.current.swipeToProceed,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun AnswerRow(title: String, answerText: String, isCorrect: Boolean) {
    Column {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
                .background(if (isCorrect) Color(0x154CAF50) else Color(0x15F44336)).padding(14.dp)
        ) {
            Text(
                text = answerText,
                color = if (isCorrect) Color(0xFF81C784) else Color(0xFFE57373),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
        }
    }
}


@Preview
@Composable
fun QuizReviewContentPreview() = EdooxTheme(
    darkTheme = false,
) {
    val stubReviewItems = QuizesDvo(
        quizTitle = "Время квиза",
        questions = listOf(
            QuizDvo.ChoiceDvo(
                questionTitle = "Which Roman Emperor legalized Christianity across the Empire with the Edict of Milan in 313 AD?",
                uiAnswers = listOf(
                    QuizDvo.ChoiceDvo.AnswerDvo(
                        text = "Nero Claudius",
                        isSelected = true,
                        isCorrect = false,
                    ),
                    QuizDvo.ChoiceDvo.AnswerDvo(
                        text = "Constantine the Great",
                        isSelected = false,
                        isCorrect = true,
                    ),
                    QuizDvo.ChoiceDvo.AnswerDvo(
                        text = "answer3",
                        isSelected = false,
                        isCorrect = true,
                    ),
                ),
                isAnswered = false,
                explanation = "While Emperor Nero famously persecuted Christians, Constantine issued the Edict of Milan, which guaranteed religious tolerance and stopped state-backed persecution."
            ), QuizDvo.FillDvo(
                questionTitle = "Вопрос?",
                correctAnswer = "answer1",
                inputTextFieldValue = "",
                isAnswered = false,
                explanation = "explanation"
            ), QuizDvo.DragAndDropDvo(
                questionTitle = "Вопрос?",
                leftColumn = listOf("answer1", "answer2", "answer3"),
                rightColumn = listOf("answer4", "answer5", "answer6"),
                map = emptyMap(),
                isAnswered = false,
                explanation = "explanation"
            )
        ),
    )

    object : CardReviewStrategy {
        override fun onCardDismissed(questionId: String, swipedRight: Boolean) {
        }
    }

    QuizReviewScreen(
        state = QuizReviewUIState(
            quiz = stubReviewItems.questions,
            title = "Время квиза",
            onPrimaryButtonText = "Запомнил",
            isFinished = false,
        ), controller = object : QuizReviewController {
            override fun onCardDismissed(swipedRight: Boolean) {
            }

            override fun onNextClicked() {
            }

            override fun onBackClicked() {
            }
        })
}


@Preview
@Composable
fun QuizReviewContentPreviewDark() = EdooxTheme(
    darkTheme = true,
) {
    val stubReviewItems = QuizesDvo(
        quizTitle = "Время квиза",
        questions = listOf(
            QuizDvo.ChoiceDvo(
                questionTitle = "Which Roman Emperor legalized Christianity across the Empire with the Edict of Milan in 313 AD?",
                uiAnswers = listOf(
                    QuizDvo.ChoiceDvo.AnswerDvo(
                        text = "Nero Claudius",
                        isSelected = true,
                        isCorrect = false,
                    ),
                    QuizDvo.ChoiceDvo.AnswerDvo(
                        text = "Constantine the Great",
                        isSelected = false,
                        isCorrect = true,
                    ),
                    QuizDvo.ChoiceDvo.AnswerDvo(
                        text = "answer3",
                        isSelected = false,
                        isCorrect = true,
                    ),
                ),
                isAnswered = false,
                explanation = "While Emperor Nero famously persecuted Christians, Constantine issued the Edict of Milan, which guaranteed religious tolerance and stopped state-backed persecution."
            ), QuizDvo.FillDvo(
                questionTitle = "Вопрос?",
                correctAnswer = "answer1",
                inputTextFieldValue = "",
                isAnswered = false,
                explanation = "explanation"
            ), QuizDvo.DragAndDropDvo(
                questionTitle = "Вопрос?",
                leftColumn = listOf("answer1", "answer2", "answer3"),
                rightColumn = listOf("answer4", "answer5", "answer6"),
                map = emptyMap(),
                isAnswered = false,
                explanation = "explanation"
            )
        ),
    )

    QuizReviewScreen(
        state = QuizReviewUIState(
            quiz = stubReviewItems.questions,
            title = "Время квиза",
            onPrimaryButtonText = "Запомнил",
            isFinished = false,
        ),
        controller = object : QuizReviewController {
            override fun onCardDismissed(swipedRight: Boolean) {
            }

            override fun onNextClicked() {
            }

            override fun onBackClicked() {
            }
        },
    )
}