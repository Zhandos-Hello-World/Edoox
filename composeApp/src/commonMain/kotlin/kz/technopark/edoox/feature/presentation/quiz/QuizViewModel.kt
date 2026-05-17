package kz.technopark.edoox.feature.presentation.quiz

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kz.technopark.edoox.feature.presentation.model.QuizDvo
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.feature.presentation.navigation.Screen
import kz.technopark.edoox.feature.presentation.review.QuizReviewLauncher

class QuizViewModel(
    private val quizLauncher: QuizLauncher,
    private val router: AppRouter,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        QuizUiState(
            title = quizLauncher.quizesDvo.quizTitle,
            questions = quizLauncher.quizesDvo.questions,
            isSubmitButtonEnabled = false,
        ),
    )
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                title = quizLauncher.quizesDvo.quizTitle,
                questions = quizLauncher.quizesDvo.questions
            )
        }
    }

    // ==========================================
    // 1. ЛОГИКА ДЛЯ CHOICE (ВЫБОР ВАРИАНТА)
    // ==========================================

    fun selectChoiceAnswer(answerIndex: Int) {
        updateCurrentQuestion<QuizDvo.ChoiceDvo> { currentQuestion ->
            val updatedAnswers = currentQuestion.uiAnswers.mapIndexed { index, answer ->
                // Если вопрос уже проверен, запрещаем менять выбор
                if (currentQuestion.isAnswered) return@updateCurrentQuestion currentQuestion

                answer.copy(isSelected = index == answerIndex)
            }

            setSubmitButtonState(true)
            currentQuestion.copy(
                uiAnswers = updatedAnswers,
            )
        }
    }

    // ==========================================
    // 2. ЛОГИКА ДЛЯ FILL (ЗАПОЛНЕНИЕ ПРОПУСКА)
    // ==========================================

    fun onFillTextChanged(text: String) {
        updateCurrentQuestion<QuizDvo.FillDvo> { currentQuestion ->
            if (currentQuestion.isAnswered) return@updateCurrentQuestion currentQuestion

            setSubmitButtonState(text.isNotBlank())
            currentQuestion.copy(
                inputTextFieldValue = text,
            )
        }
    }

    // ==========================================
    // 3. ЛОГИКА ДЛЯ DRAG_AND_DROP (СОПОСТАВЛЕНИЕ)
    // ==========================================
    fun onDragAndDropMatch(userPairs: Map<String, String>) {
        updateCurrentQuestion<QuizDvo.DragAndDropDvo> { currentQuestion ->
            if (currentQuestion.isAnswered) return@updateCurrentQuestion currentQuestion

            // Кнопка проверки активна, если сопоставлены все элементы колонки
            val allMatched = userPairs.size == currentQuestion.leftColumn.size

            setSubmitButtonState(allMatched)
            currentQuestion.copy(
                userMatches = userPairs,
            )
        }
    }

    // ==========================================
    // 4. ЛОГИКА ПРОВЕРКИ И НАВИГАЦИИ
    // ==========================================
    fun handleActionClick() {
        val currentQuestion = _uiState.value.currentQuestion ?: return

        if (!currentQuestion.isAnswered) {
            checkCurrentAnswer()
        } else {
            navigateToNextStep()
        }
    }

    private fun checkCurrentAnswer() {
        var isCorrectAnswer = false

        updateCurrentQuestion<QuizDvo> { currentQuestion ->
            when (currentQuestion) {
                is QuizDvo.ChoiceDvo -> {
                    val selectedAnswer = currentQuestion.uiAnswers.find { it.isSelected }
                    isCorrectAnswer = selectedAnswer?.isCorrect == true

                    val validatedAnswers = currentQuestion.uiAnswers.map { answer ->
                        val color = when {
                            answer.isCorrect -> Color.Green.value // Зеленый (Правильный)
                            answer.isSelected && !answer.isCorrect -> Color.Red.value // Красный (Ошибка)
                            else -> Color.Transparent.value
                        }
                        answer.copy(selectedColor = color)
                    }
                    currentQuestion.copy(
                        uiAnswers = validatedAnswers,
                        isAnswered = true,
                    )
                }

                is QuizDvo.FillDvo -> {
                    isCorrectAnswer = currentQuestion.inputTextFieldValue.trim()
                        .equals(currentQuestion.correctAnswer.trim(), ignoreCase = true)

                    if (isCorrectAnswer) "Правильно!" else "Неверно. Правильный ответ: ${currentQuestion.correctAnswer}"
                    currentQuestion.copy(isAnswered = true)
                }

                is QuizDvo.DragAndDropDvo -> {

                    isCorrectAnswer = currentQuestion.userMatches == currentQuestion.map

                    currentQuestion.copy(isAnswered = true)
                }
            }
        }

        if (isCorrectAnswer) {
            _uiState.update { it.copy(score = it.score + 1) }
        }
    }

    private fun navigateToNextStep() {
        setSubmitButtonState(false)
        val currentState = _uiState.value
        val nextIndex = currentState.currentQuestionIndex + 1

        if (nextIndex < currentState.questions.size) {
            _uiState.update { it.copy(currentQuestionIndex = nextIndex) }
        } else {
            _uiState.update { it.copy(isQuizFinished = true) }
            router.replace(
                Screen.QuizReview(
                    quizReviewLauncher = QuizReviewLauncher(
                        dvo = currentState.questions,
                    ),
                ),
            )
        }
    }

    private inline fun <reified T : QuizDvo> updateCurrentQuestion(crossinline block: (T) -> QuizDvo) {
        val currentIndex = _uiState.value.currentQuestionIndex
        val updatedQuestions = _uiState.value.questions.mapIndexed { index, question ->
            if (index == currentIndex && question is T) {
                block(question)
            } else {
                question
            }
        }
        _uiState.update { it.copy(questions = updatedQuestions) }
    }

    private fun setSubmitButtonState(isEnabled: Boolean) {
        _uiState.update {
            it.copy(isSubmitButtonEnabled = isEnabled)
        }
    }
}