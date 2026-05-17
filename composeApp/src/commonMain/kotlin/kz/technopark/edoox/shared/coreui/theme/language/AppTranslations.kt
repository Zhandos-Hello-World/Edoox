package kz.technopark.edoox.shared.coreui.theme.language

interface AppTranslations {
    val start: String
    val switchLanguage: String

    // Статусы загрузки
    val loadingMaterials: String
    val calibratingNeuralNetwork: String
    val generatingCustomQuiz: String
    val almostReady: String

    val chooseSubject: String
    val math: String
    val historyOfKazakhstan: String
    val biology: String
    val kazakhLanguage: String
    val continueText: String

    // Динамические номера вопросов
    fun questionNumber(number: Int): String

    val check: String
    val enterAnswer: String
    val next: String
    val errorCorrection: String
    val incorrect: String
    val yourSelection: String
    val correctAnswer: String
    val explanation: String

    // Динамические объяснения
    fun explanationForQuestion(number: Int): String

    val swipeToProceed: String
    val remembered: String
    val allCaughtUp: String
    val oopsSomethingWrong: String
    val errorLoadingData: String
    val tryAgain: String
    val backToHome: String

    val errorNoInternet: String
    val errorServerError: String
    val errorGeminiLimitExceeded: String
    val errorUnknown: String
}