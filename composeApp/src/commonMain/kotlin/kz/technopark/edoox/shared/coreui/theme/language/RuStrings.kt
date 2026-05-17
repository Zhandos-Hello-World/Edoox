package kz.technopark.edoox.shared.coreui.theme.language

object RuStrings : AppTranslations {
    override val start = "Начать"
    override val switchLanguage = "Сменить язык"

    override val loadingMaterials = "Материалы загружаются"
    override val calibratingNeuralNetwork = "Нейросеть калибруется"
    override val generatingCustomQuiz = "Создается кастомный квиз"
    override val almostReady = "Почти готово"

    override val chooseSubject = "Выберите предмет"
    override val math = "Математика"
    override val historyOfKazakhstan = "История Казахстана"
    override val biology = "Биология"
    override val kazakhLanguage = "Казахский язык"
    override val continueText = "Продолжить"

    override fun questionNumber(number: Int): String = "Вопрос #$number"

    override val check = "Проверить"
    override val enterAnswer = "Введите ответ"
    override val next = "Дальше"
    override val errorCorrection = "Работа над ошибками"

    // Переведенные англоязычные строки:
    override val incorrect = "НЕВЕРНО"
    override val yourSelection = "Ваш выбор:"
    override val correctAnswer = "Правильный ответ:"
    override val explanation = "Объяснение"

    override fun explanationForQuestion(number: Int): String = "Объяснение к вопросу №$number"

    override val swipeToProceed = "Смахните влево или вправо, чтобы продолжить"
    override val remembered = "Запомнил"
    override val allCaughtUp = "Всё готово!"

    override val oopsSomethingWrong = "Ой! Что-то пошло не так"
    override val errorLoadingData = "Произошла ошибка при загрузке данных"
    override val tryAgain = "Попробовать снова"
    override val backToHome = "Вернуться на главную"

    override val errorNoInternet = "Нет интернет-соединения"
    override val errorServerError = "Ошибка сервера"
    override val errorGeminiLimitExceeded = "Лимит API Gemini превышен"
    override val errorUnknown = "Произошла ошибка при загрузке данных"
}